
package lsc.businesslogic.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lsc.businesslogic.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegisterRecordResponse_QNAME = new QName("http://ws.businesslogic.lsc/", "register_recordResponse");
    private final static QName _AggregatedData_QNAME = new QName("http://ws.businesslogic.lsc/", "aggregatedData");
    private final static QName _Record_QNAME = new QName("http://ws.businesslogic.lsc/", "record");
    private final static QName _Statistic_QNAME = new QName("http://ws.businesslogic.lsc/", "statistic");
    private final static QName _CompleteTodo_QNAME = new QName("http://ws.businesslogic.lsc/", "complete_todo");
    private final static QName _ComputeStatisticResponse_QNAME = new QName("http://ws.businesslogic.lsc/", "compute_statisticResponse");
    private final static QName _UpdateTodayGoalAndNotification_QNAME = new QName("http://ws.businesslogic.lsc/", "update_today_goal_and_notification");
    private final static QName _ComputeStatistic_QNAME = new QName("http://ws.businesslogic.lsc/", "compute_statistic");
    private final static QName _CompleteTodoResponse_QNAME = new QName("http://ws.businesslogic.lsc/", "complete_todoResponse");
    private final static QName _RegisterGoal_QNAME = new QName("http://ws.businesslogic.lsc/", "register_goal");
    private final static QName _RegisterRecord_QNAME = new QName("http://ws.businesslogic.lsc/", "register_record");
    private final static QName _Goal_QNAME = new QName("http://ws.businesslogic.lsc/", "goal");
    private final static QName _RegisterGoalResponse_QNAME = new QName("http://ws.businesslogic.lsc/", "register_goalResponse");
    private final static QName _UpdateTodayGoalAndNotificationResponse_QNAME = new QName("http://ws.businesslogic.lsc/", "update_today_goal_and_notificationResponse");
    private final static QName _Data_QNAME = new QName("http://ws.businesslogic.lsc/", "data");
    private final static QName _RecordDatas_QNAME = new QName("", "datas");
    private final static QName _RecordId_QNAME = new QName("", "id");
    private final static QName _RecordExternalId_QNAME = new QName("", "external_id");
    private final static QName _RecordExternalResource_QNAME = new QName("", "external_resource");
    private final static QName _RecordDate_QNAME = new QName("", "date");
    private final static QName _RecordType_QNAME = new QName("", "type");
    private final static QName _GoalDays_QNAME = new QName("", "days");
    private final static QName _GoalRecordType_QNAME = new QName("", "record_type");
    private final static QName _GoalDataName_QNAME = new QName("", "data_name");
    private final static QName _GoalValue_QNAME = new QName("", "value");
    private final static QName _GoalRepeat_QNAME = new QName("", "repeat");
    private final static QName _GoalPerc_QNAME = new QName("", "perc");
    private final static QName _GoalReference_QNAME = new QName("", "reference");
    private final static QName _GoalOperator_QNAME = new QName("", "operator");
    private final static QName _GoalFunction_QNAME = new QName("", "function");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lsc.businesslogic.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Record }
     * 
     */
    public Record createRecord() {
        return new Record();
    }

    /**
     * Create an instance of {@link RegisterGoal }
     * 
     */
    public RegisterGoal createRegisterGoal() {
        return new RegisterGoal();
    }

    /**
     * Create an instance of {@link UpdateTodayGoalAndNotificationResponse }
     * 
     */
    public UpdateTodayGoalAndNotificationResponse createUpdateTodayGoalAndNotificationResponse() {
        return new UpdateTodayGoalAndNotificationResponse();
    }

    /**
     * Create an instance of {@link Data }
     * 
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link RegisterGoalResponse }
     * 
     */
    public RegisterGoalResponse createRegisterGoalResponse() {
        return new RegisterGoalResponse();
    }

    /**
     * Create an instance of {@link RegisterRecord }
     * 
     */
    public RegisterRecord createRegisterRecord() {
        return new RegisterRecord();
    }

    /**
     * Create an instance of {@link Goal }
     * 
     */
    public Goal createGoal() {
        return new Goal();
    }

    /**
     * Create an instance of {@link Statistic }
     * 
     */
    public Statistic createStatistic() {
        return new Statistic();
    }

    /**
     * Create an instance of {@link StatisticData }
     * 
     */
    public StatisticData createStatisticData() {
        return new StatisticData();
    }

    /**
     * Create an instance of {@link ComputeStatisticResponse }
     * 
     */
    public ComputeStatisticResponse createComputeStatisticResponse() {
        return new ComputeStatisticResponse();
    }

    /**
     * Create an instance of {@link CompleteTodo }
     * 
     */
    public CompleteTodo createCompleteTodo() {
        return new CompleteTodo();
    }

    /**
     * Create an instance of {@link RegisterRecordResponse }
     * 
     */
    public RegisterRecordResponse createRegisterRecordResponse() {
        return new RegisterRecordResponse();
    }

    /**
     * Create an instance of {@link CompleteTodoResponse }
     * 
     */
    public CompleteTodoResponse createCompleteTodoResponse() {
        return new CompleteTodoResponse();
    }

    /**
     * Create an instance of {@link UpdateTodayGoalAndNotification }
     * 
     */
    public UpdateTodayGoalAndNotification createUpdateTodayGoalAndNotification() {
        return new UpdateTodayGoalAndNotification();
    }

    /**
     * Create an instance of {@link ComputeStatistic }
     * 
     */
    public ComputeStatistic createComputeStatistic() {
        return new ComputeStatistic();
    }

    /**
     * Create an instance of {@link Record.Datas }
     * 
     */
    public Record.Datas createRecordDatas() {
        return new Record.Datas();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "register_recordResponse")
    public JAXBElement<RegisterRecordResponse> createRegisterRecordResponse(RegisterRecordResponse value) {
        return new JAXBElement<RegisterRecordResponse>(_RegisterRecordResponse_QNAME, RegisterRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatisticData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "aggregatedData")
    public JAXBElement<StatisticData> createAggregatedData(StatisticData value) {
        return new JAXBElement<StatisticData>(_AggregatedData_QNAME, StatisticData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Record }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "record")
    public JAXBElement<Record> createRecord(Record value) {
        return new JAXBElement<Record>(_Record_QNAME, Record.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Statistic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "statistic")
    public JAXBElement<Statistic> createStatistic(Statistic value) {
        return new JAXBElement<Statistic>(_Statistic_QNAME, Statistic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteTodo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "complete_todo")
    public JAXBElement<CompleteTodo> createCompleteTodo(CompleteTodo value) {
        return new JAXBElement<CompleteTodo>(_CompleteTodo_QNAME, CompleteTodo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputeStatisticResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "compute_statisticResponse")
    public JAXBElement<ComputeStatisticResponse> createComputeStatisticResponse(ComputeStatisticResponse value) {
        return new JAXBElement<ComputeStatisticResponse>(_ComputeStatisticResponse_QNAME, ComputeStatisticResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTodayGoalAndNotification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "update_today_goal_and_notification")
    public JAXBElement<UpdateTodayGoalAndNotification> createUpdateTodayGoalAndNotification(UpdateTodayGoalAndNotification value) {
        return new JAXBElement<UpdateTodayGoalAndNotification>(_UpdateTodayGoalAndNotification_QNAME, UpdateTodayGoalAndNotification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputeStatistic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "compute_statistic")
    public JAXBElement<ComputeStatistic> createComputeStatistic(ComputeStatistic value) {
        return new JAXBElement<ComputeStatistic>(_ComputeStatistic_QNAME, ComputeStatistic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteTodoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "complete_todoResponse")
    public JAXBElement<CompleteTodoResponse> createCompleteTodoResponse(CompleteTodoResponse value) {
        return new JAXBElement<CompleteTodoResponse>(_CompleteTodoResponse_QNAME, CompleteTodoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterGoal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "register_goal")
    public JAXBElement<RegisterGoal> createRegisterGoal(RegisterGoal value) {
        return new JAXBElement<RegisterGoal>(_RegisterGoal_QNAME, RegisterGoal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "register_record")
    public JAXBElement<RegisterRecord> createRegisterRecord(RegisterRecord value) {
        return new JAXBElement<RegisterRecord>(_RegisterRecord_QNAME, RegisterRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Goal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "goal")
    public JAXBElement<Goal> createGoal(Goal value) {
        return new JAXBElement<Goal>(_Goal_QNAME, Goal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterGoalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "register_goalResponse")
    public JAXBElement<RegisterGoalResponse> createRegisterGoalResponse(RegisterGoalResponse value) {
        return new JAXBElement<RegisterGoalResponse>(_RegisterGoalResponse_QNAME, RegisterGoalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTodayGoalAndNotificationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "update_today_goal_and_notificationResponse")
    public JAXBElement<UpdateTodayGoalAndNotificationResponse> createUpdateTodayGoalAndNotificationResponse(UpdateTodayGoalAndNotificationResponse value) {
        return new JAXBElement<UpdateTodayGoalAndNotificationResponse>(_UpdateTodayGoalAndNotificationResponse_QNAME, UpdateTodayGoalAndNotificationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Data }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.businesslogic.lsc/", name = "data")
    public JAXBElement<Data> createData(Data value) {
        return new JAXBElement<Data>(_Data_QNAME, Data.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Record.Datas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "datas", scope = Record.class)
    public JAXBElement<Record.Datas> createRecordDatas(Record.Datas value) {
        return new JAXBElement<Record.Datas>(_RecordDatas_QNAME, Record.Datas.class, Record.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id", scope = Record.class)
    public JAXBElement<Integer> createRecordId(Integer value) {
        return new JAXBElement<Integer>(_RecordId_QNAME, Integer.class, Record.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "external_id", scope = Record.class)
    public JAXBElement<Integer> createRecordExternalId(Integer value) {
        return new JAXBElement<Integer>(_RecordExternalId_QNAME, Integer.class, Record.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "external_resource", scope = Record.class)
    public JAXBElement<String> createRecordExternalResource(String value) {
        return new JAXBElement<String>(_RecordExternalResource_QNAME, String.class, Record.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "date", scope = Record.class)
    public JAXBElement<String> createRecordDate(String value) {
        return new JAXBElement<String>(_RecordDate_QNAME, String.class, Record.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = Record.class)
    public JAXBElement<String> createRecordType(String value) {
        return new JAXBElement<String>(_RecordType_QNAME, String.class, Record.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id", scope = Goal.class)
    public JAXBElement<Integer> createGoalId(Integer value) {
        return new JAXBElement<Integer>(_RecordId_QNAME, Integer.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "external_id", scope = Goal.class)
    public JAXBElement<Integer> createGoalExternalId(Integer value) {
        return new JAXBElement<Integer>(_RecordExternalId_QNAME, Integer.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "external_resource", scope = Goal.class)
    public JAXBElement<String> createGoalExternalResource(String value) {
        return new JAXBElement<String>(_RecordExternalResource_QNAME, String.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "days", scope = Goal.class)
    public JAXBElement<String> createGoalDays(String value) {
        return new JAXBElement<String>(_GoalDays_QNAME, String.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "record_type", scope = Goal.class)
    public JAXBElement<String> createGoalRecordType(String value) {
        return new JAXBElement<String>(_GoalRecordType_QNAME, String.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "data_name", scope = Goal.class)
    public JAXBElement<String> createGoalDataName(String value) {
        return new JAXBElement<String>(_GoalDataName_QNAME, String.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "value", scope = Goal.class)
    public JAXBElement<String> createGoalValue(String value) {
        return new JAXBElement<String>(_GoalValue_QNAME, String.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Interval }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "repeat", scope = Goal.class)
    public JAXBElement<Interval> createGoalRepeat(Interval value) {
        return new JAXBElement<Interval>(_GoalRepeat_QNAME, Interval.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Perc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "perc", scope = Goal.class)
    public JAXBElement<Perc> createGoalPerc(Perc value) {
        return new JAXBElement<Perc>(_GoalPerc_QNAME, Perc.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "reference", scope = Goal.class)
    public JAXBElement<Reference> createGoalReference(Reference value) {
        return new JAXBElement<Reference>(_GoalReference_QNAME, Reference.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Operator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "operator", scope = Goal.class)
    public JAXBElement<Operator> createGoalOperator(Operator value) {
        return new JAXBElement<Operator>(_GoalOperator_QNAME, Operator.class, Goal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Function }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "function", scope = Goal.class)
    public JAXBElement<Function> createGoalFunction(Function value) {
        return new JAXBElement<Function>(_GoalFunction_QNAME, Function.class, Goal.class, value);
    }

}
