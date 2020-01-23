//package com.crud.tasks.trello.validator;
//
//import com.crud.tasks.domain.TrelloCard;
//import org.junit.After;
//import org.junit.Test;
//import uk.org.lidalia.slf4jtest.TestLogger;
//import uk.org.lidalia.slf4jtest.TestLoggerFactory;
//
//import java.util.Collections;
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;
//import static uk.org.lidalia.slf4jtest.LoggingEvent.info;
//
//public class TrelloValidatorTestSuit {
//
//    private TrelloValidator trelloValidator = new TrelloValidator();
//    private TestLogger logger = TestLoggerFactory.getTestLogger(TrelloValidator.class);
//
//    @Test
//    public void testValidateCardProduction() {
//        TrelloCard trelloCard = new TrelloCard("name", "des", "pos", "id");
//        trelloValidator.validateCard(trelloCard);
//        assertThat(logger.getLoggingEvents(), is(Collections.singletonList(info("Seams my application is used in proper way"))));
//    }
//
//    @Test
//    public void testValidateCardTesting() {
//        TrelloCard trelloCard = new TrelloCard("test", "test", "pos", "id");
//        trelloValidator.validateCard(trelloCard);
//        assertThat(logger.getLoggingEvents(), is(Collections.singletonList(info("Someone is testing my application!"))));
//    }
//
//    @After
//    public void clearLoggers() {
//        TestLoggerFactory.clear();
//    }
//}
