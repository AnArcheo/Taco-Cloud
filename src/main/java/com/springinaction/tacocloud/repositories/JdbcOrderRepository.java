/*
Class no longer needed - as Spring Data automatically creates implementation
of the OrderRepository
 */



//package com.springinaction.tacocloud.repositories;
//
//import com.springinaction.tacocloud.domains.Ingredient;
//import com.springinaction.tacocloud.domains.IngredientRef;
//import com.springinaction.tacocloud.domains.Taco;
//import com.springinaction.tacocloud.domains.TacoOrder;
//import org.springframework.asm.Type;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository{
//
//    private JdbcOperations jdbcOperations;
//
//    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
//        this.jdbcOperations = jdbcOperations;
//    }
//
//    @Override
//    @Transactional
//    public TacoOrder save(TacoOrder order) {
//
//        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
//               "insert into Taco_Order "
//                       + "(delivery_name, delivery_street, delivery_city, "
//                       + "delivery_state, delivery_zip, cc_number, "
//                       + "cc_expiration, cc_cvv, placed_at)"
//                       + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
//                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
//        );
//        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
//
//        order.setPlacedAt(new Date());
//
//        PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(
//                Arrays.asList(
//                        order.getDeliveryName(),
//                        order.getDeliveryStreet(),
//                        order.getDeliveryCity(),
//                        order.getDeliveryState(),
//                        order.getDeliveryZip(),
//                        order.getCcNumber(),
//                        order.getCcExpiration(),
//                        order.getCcCVV(),
//                        order.getPlacedAt()
//                )
//        );
//
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long orderId = keyHolder.getKey().longValue();
//        order.setId(orderId);
//
//        List<Taco> tacos = order.getTacos();
//        int i = 0;
//        for(Taco taco : tacos){
//            saveTaco(orderId, i++, taco);
//        }
//        return order;
//    }
//
//    private long saveTaco(Long orderId, int orderKey, Taco taco) {
//        taco.setCreateAt(new Date());
//        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
//                "insert into Taco "
//                + "(name, created_at, taco_order, taco_order_key) "
//                + "values(?, ?, ?, ?)",
//                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
//        );
//        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
//
//        PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(
//              Arrays.asList(
//                      taco.getName(),
//                      taco.getCreateAt(),
//                      orderId,
//                      orderKey
//              )
//        );
//
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long tacoId = keyHolder.getKey().longValue();
//        taco.setId(tacoId);
//
//        saveIngredientRef(tacoId, taco.getIngredients());
//
//        return tacoId;
//    }
//
//    private void saveIngredientRef(long tacoId, List<Ingredient> ingredients) {
//        int key = 0;
//        for(Ingredient ingredient : ingredients){
//            jdbcOperations.update(
//                  "insert into Ingredient_Ref (ingredient, taco, taco_key)"
//                  + "values (?, ?, ?)",
//                  ingredient.getName(), tacoId, key++);
//        }
//    }
//}
