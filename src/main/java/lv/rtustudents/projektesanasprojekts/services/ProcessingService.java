package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.models.Price;
import lv.rtustudents.projektesanasprojekts.repositories.OrderRepo;
import lv.rtustudents.projektesanasprojekts.repositories.PriceRepo;
import lv.rtustudents.projektesanasprojekts.utils.Constants;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class ProcessingService {

    OrderRepo orderRepo;
    PriceRepo priceRepo;

    ProcessingService(OrderRepo orderRepo, PriceRepo priceRepo) {
        this.orderRepo = orderRepo;
        this.priceRepo = priceRepo;
    }

    public String process() {
        log.info("PROCESS ORDERS | Processing started");
        List<Order> orders = orderRepo.findAllByStatus(Constants.STATUS_WAITING);

        if (orders.isEmpty()) {
            log.warn("PROCESS ORDERS | No orders in waiting status");
            return null;
        }

        Integer orderCount = orders.size();
        JSONObject result = new JSONObject();

        Collection<LinearConstraint> constraints = new ArrayList<>();
        Collection<Float> allpricePerBook = new ArrayList<>();
        Collection<Float> all_binding_Time = new ArrayList<>();
        Collection<Float> all_covering_Time = new ArrayList<>();
        Collection<Float> all_cutting_Time = new ArrayList<>();

        for (Order order : orders) {
            Float pricePerBook = createPricePerBook(order);
            System.out.println(pricePerBook);

            allpricePerBook.add(pricePerBook);
            all_binding_Time.add(order.getBindingTimePer());
            all_covering_Time.add(order.getCoveringTimePer());
            all_cutting_Time.add(order.getCuttingTimePer());

            result.put(String.valueOf(order.getId()), new JSONObject().put("pricePerBook", pricePerBook).put("solution", -1));
        }

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            double[] coefficients = new double[orderCount];
            Arrays.fill(coefficients,0);
            coefficients[i] = 1;  // Set the coefficient for the current book to 1
            constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, order.getAmount()));
        }


        double[] all_binding_TimeArray = all_binding_Time.stream().mapToDouble(Float::doubleValue).toArray();
        constraints.add(new LinearConstraint(all_binding_TimeArray, Relationship.LEQ, 57600));

        double[] all_covering_TimeArray = all_covering_Time.stream().mapToDouble(Float::doubleValue).toArray();
        constraints.add(new LinearConstraint(all_covering_TimeArray, Relationship.LEQ, 57600));

        double[] all_cutting_TimeArray = all_cutting_Time.stream().mapToDouble(Float::doubleValue).toArray();
        constraints.add(new LinearConstraint(all_cutting_TimeArray, Relationship.LEQ, 57600));

        double[] allpricePerBookArray = allpricePerBook.stream().mapToDouble(Float::doubleValue).toArray();
        LinearObjectiveFunction f = new LinearObjectiveFunction(allpricePerBookArray, 0);


        OptimizationData[] optData = new OptimizationData[] {GoalType.MAXIMIZE, f, new LinearConstraintSet(constraints), new
                NonNegativeConstraint(true)};

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(optData);

        double[] solverSolution = solution.getPoint();

        for (int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            JSONObject obj = (JSONObject) result.get(String.valueOf(order.getId()));
            obj.put("solution", solverSolution[i]);

            if (solverSolution[i] > 0) {
                order.setStatus(Constants.STATUS_COMPLETE);
                orderRepo.save(order);
            }
        }

        log.info("Solution: " + Arrays.toString(solverSolution));

        log.info("Processing finished");
        log.info(result.toString());
        return result.toString();
    }

    private Float createPricePerBook(Order order) {
        Price prices = priceRepo.findById(1L).get();
        Float pricePerBook = prices.getPerPage() * order.getPageCount();

        switch (order.getCoverType()) {
            case Constants.COVER_TYPE_HARD: pricePerBook += prices.getHardCover(); break;
            case Constants.COVER_TYPE_SOFT: pricePerBook += prices.getSoftCover(); break;
            case Constants.COVER_TYPE_PAPER: pricePerBook += prices.getPaperCover(); break;
        }
        switch (order.getBindingType()) {
            case Constants.BINDING_TYPE_STITCH: pricePerBook += prices.getBindingStitch(); break;
            case Constants.BINDING_TYPE_PERFECT: pricePerBook += prices.getBindingPerfect(); break;
            case Constants.BINDING_TYPE_SPIRAL: pricePerBook += prices.getBindingSpiral(); break;
        }
        switch (order.getFormat()) {
            case Constants.FORMAT_ALBUM: pricePerBook += prices.getFormatAlbum(); break;
            case Constants.FORMAT_PORTRAIT: pricePerBook += prices.getFormatPortrait(); break;
        }

        return pricePerBook;
    }
}
