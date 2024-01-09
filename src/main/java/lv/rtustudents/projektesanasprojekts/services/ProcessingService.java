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

        // Define the constraints
        Collection<LinearConstraint> constraints = new ArrayList<>();
        Double[] objectiveFunctionArray = new Double[orderCount];

        for (Order order : orders) {
            Float pricePerBook = createPricePerBook(order);
            System.out.println(pricePerBook);
            order.setStatus(Constants.STATUS_COMPLETE);
            constraints.add(new LinearConstraint(new double[] {order.getPageCount(), 1}, Relationship.LEQ, order.getAmount()));
        }

        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] {orderCount, 5}, 0);

//        constraints.add(new LinearConstraint(new double[] {2, 1}, Relationship.LEQ, 6));
//        constraints.add(new LinearConstraint(new double[] {1, 2}, Relationship.LEQ, 8));
//        constraints.add(new LinearConstraint(new double[] {1, 1}, Relationship.GEQ, 0));

        OptimizationData[] optData = new OptimizationData[] {GoalType.MAXIMIZE, f, new LinearConstraintSet(constraints)};

        // Create and run the solver
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(optData);

        // Print the solution
        log.info("Solution: " + Arrays.toString(solution.getPoint()));

        log.info("Processing finished");
        return Arrays.toString(solution.getPoint());
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
