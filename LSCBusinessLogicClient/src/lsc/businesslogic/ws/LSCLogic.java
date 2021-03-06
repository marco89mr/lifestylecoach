
package lsc.businesslogic.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LSCLogic", targetNamespace = "http://ws.businesslogic.lsc/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LSCLogic {


    /**
     * 
     * @param onInterval
     * @param to
     * @param userId
     * @param from
     * @param fieldName
     * @param recordType
     * @param function
     * @return
     *     returns lsc.businesslogic.ws.Statistic
     */
    @WebMethod(operationName = "compute_statistic")
    @WebResult(name = "Statistic", targetNamespace = "")
    @RequestWrapper(localName = "compute_statistic", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.ComputeStatistic")
    @ResponseWrapper(localName = "compute_statisticResponse", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.ComputeStatisticResponse")
    @Action(input = "http://ws.businesslogic.lsc/LSCLogic/compute_statisticRequest", output = "http://ws.businesslogic.lsc/LSCLogic/compute_statisticResponse")
    public Statistic computeStatistic(
        @WebParam(name = "user_id", targetNamespace = "")
        int userId,
        @WebParam(name = "record_type", targetNamespace = "")
        String recordType,
        @WebParam(name = "field_name", targetNamespace = "")
        String fieldName,
        @WebParam(name = "from", targetNamespace = "")
        String from,
        @WebParam(name = "to", targetNamespace = "")
        String to,
        @WebParam(name = "on_interval", targetNamespace = "")
        Interval onInterval,
        @WebParam(name = "function", targetNamespace = "")
        Function function);

    /**
     * 
     * @param record
     * @return
     *     returns int
     */
    @WebMethod(operationName = "register_record")
    @WebResult(name = "record", targetNamespace = "")
    @RequestWrapper(localName = "register_record", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.RegisterRecord")
    @ResponseWrapper(localName = "register_recordResponse", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.RegisterRecordResponse")
    @Action(input = "http://ws.businesslogic.lsc/LSCLogic/register_recordRequest", output = "http://ws.businesslogic.lsc/LSCLogic/register_recordResponse")
    public int registerRecord(
        @WebParam(name = "record", targetNamespace = "http://ws.businesslogic.lsc/")
        Record record);

    /**
     * 
     * @param userId
     * @return
     *     returns int
     */
    @WebMethod(operationName = "update_today_goal_and_notification")
    @WebResult(name = "void", targetNamespace = "")
    @RequestWrapper(localName = "update_today_goal_and_notification", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.UpdateTodayGoalAndNotification")
    @ResponseWrapper(localName = "update_today_goal_and_notificationResponse", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.UpdateTodayGoalAndNotificationResponse")
    @Action(input = "http://ws.businesslogic.lsc/LSCLogic/update_today_goal_and_notificationRequest", output = "http://ws.businesslogic.lsc/LSCLogic/update_today_goal_and_notificationResponse")
    public int updateTodayGoalAndNotification(
        @WebParam(name = "user_id", targetNamespace = "")
        int userId);

    /**
     * 
     * @param todoId
     * @return
     *     returns int
     */
    @WebMethod(operationName = "complete_todo")
    @WebResult(name = "void", targetNamespace = "")
    @RequestWrapper(localName = "complete_todo", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.CompleteTodo")
    @ResponseWrapper(localName = "complete_todoResponse", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.CompleteTodoResponse")
    @Action(input = "http://ws.businesslogic.lsc/LSCLogic/complete_todoRequest", output = "http://ws.businesslogic.lsc/LSCLogic/complete_todoResponse")
    public int completeTodo(
        @WebParam(name = "todo_id", targetNamespace = "")
        int todoId);

    /**
     * 
     * @param endDate
     * @param goal
     * @return
     *     returns int
     */
    @WebMethod(operationName = "register_goal")
    @WebResult(name = "void", targetNamespace = "")
    @RequestWrapper(localName = "register_goal", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.RegisterGoal")
    @ResponseWrapper(localName = "register_goalResponse", targetNamespace = "http://ws.businesslogic.lsc/", className = "lsc.businesslogic.ws.RegisterGoalResponse")
    @Action(input = "http://ws.businesslogic.lsc/LSCLogic/register_goalRequest", output = "http://ws.businesslogic.lsc/LSCLogic/register_goalResponse")
    public int registerGoal(
        @WebParam(name = "goal", targetNamespace = "http://ws.businesslogic.lsc/")
        Goal goal,
        @WebParam(name = "end_date", targetNamespace = "")
        String endDate);

}
