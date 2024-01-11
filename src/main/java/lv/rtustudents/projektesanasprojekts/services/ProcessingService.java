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
        Collection<Float> allpricePerBook = new ArrayList<>();
        Collection<Float> all_binding_Time = new ArrayList<>();
        Collection<Float> all_covering_Time = new ArrayList<>();
        Collection<Float> all_cutting_Time = new ArrayList<>();

        Double[] objectiveFunctionArray = new Double[orderCount];

        for (Order order : orders) {
            Float pricePerBook = createPricePerBook(order);
            System.out.println(pricePerBook);
            order.setStatus(Constants.STATUS_COMPLETE);
            allpricePerBook.add(pricePerBook);
            all_binding_Time.add(order.getBindingTimePer());
            all_covering_Time.add(order.getCoveringTimePer());
            all_cutting_Time.add(order.getCuttingTimePer());

            //constraints.add(new LinearConstraint(new double[] {1}, Relationship.LEQ, order.getAmount()));
        }

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            double[] coefficients = new double[orderCount];
            Arrays.fill(coefficients,0);
            coefficients[i] = 1;  // Set the coefficient for the current book to 1
            //log.info(Arrays.toString(coefficients));
            // Add the constraint for the current book
            constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, order.getAmount()));
        }


        double[] all_binding_TimeArray = all_binding_Time.stream().mapToDouble(Float::doubleValue).toArray();
        constraints.add(new LinearConstraint(all_binding_TimeArray, Relationship.LEQ, 960));

        double[] all_covering_TimeArray = all_covering_Time.stream().mapToDouble(Float::doubleValue).toArray();
        constraints.add(new LinearConstraint(all_covering_TimeArray, Relationship.LEQ, 960));

        double[] all_cutting_TimeArray = all_cutting_Time.stream().mapToDouble(Float::doubleValue).toArray();
        constraints.add(new LinearConstraint(all_cutting_TimeArray, Relationship.LEQ, 960));

        double[] allpricePerBookArray = allpricePerBook.stream().mapToDouble(Float::doubleValue).toArray();
        LinearObjectiveFunction f = new LinearObjectiveFunction(allpricePerBookArray, 0);


        OptimizationData[] optData = new OptimizationData[] {GoalType.MAXIMIZE, f, new LinearConstraintSet(constraints), new
                NonNegativeConstraint(true)};

        // Create and run the solver
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(optData);

        //double[] getSolution = new double[orderCount];
        double[] solverSolution = solution.getPoint();

        for (int i = 0; i < solverSolution.length; i++){
            Order order = orders.get(i);
            order.setStatus(Constants.STATUS_COMPLETE);
            orderRepo.save(order);
            solverSolution[i] = solverSolution[i] * order.getAmount();
        }

        // Print the solution
        log.info("Solution: " + Arrays.toString(solverSolution));

        log.info("Processing finished");
        return Arrays.toString(solverSolution);
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



    /* m  = count of columns
    for(int j=0; j<m; j++)
    {
        double[] v = new double[n*m];
        for(int i=0; i<n; i++)
            v[i*n + j] = 1;
        constraints.add(new LinearConstraint(v, Relationship.LEQ, 1));
    }

    // n = count of rows
    for(int i=0; i<n; i++)
    {
        double[] v = new double[n*m];
        for(int j=0; j<m; j++)
            v[i*n + j] = A[i][j];
        constraints.add(new LinearConstraint(v, Relationship.LEQ, L));
    }
    double[] objectiveCoefficients = new double[n * m];
    Arrays.fill(objectiveCoefficients, 1.0);
    LinearObjectiveFunction objective = LinearObjectiveFunction(objectiveCoefficients, 0);
    LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] {orderCount, 5}, 0);*/


}
