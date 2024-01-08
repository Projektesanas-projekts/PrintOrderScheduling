package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.repositories.OrderRepo;
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

    ProcessingService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public String process() {
        log.info("PROCESS ORDERS | Processing started");
        List<Order> orders = orderRepo.findAllByStatus(Constants.STATUS_WAITING);

        if (orders.isEmpty()) {
            log.warn("PROCESS ORDERS | No orders in waiting status");
            return null;
        }

        Integer orderCount = orders.size();

        // TODO: Algorithm to maximize profit using orders and machinery prices
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] {orderCount, 5}, 0);

        // Define the constraints
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

        for (Order order : orders) {
            constraints.add(new LinearConstraint(new double[] {order.getPageCount(), 1}, Relationship.LEQ, order.getAmount()));
        }

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
}
