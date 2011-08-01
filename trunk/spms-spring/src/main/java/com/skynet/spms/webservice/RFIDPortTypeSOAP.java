package com.skynet.spms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.skynet.spms.webservice.entity.AllUsersInputParameters;
import com.skynet.spms.webservice.entity.AllUsersOutParameters;
import com.skynet.spms.webservice.entity.Check4DoorControlInputParameters;
import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.GetRFIDSerialInputParameters;
import com.skynet.spms.webservice.entity.GetRFIDSerialOutputParameters;
import com.skynet.spms.webservice.entity.GetTaskDetailsInputParameters;
import com.skynet.spms.webservice.entity.GetTaskDetailsOutputParameters;
import com.skynet.spms.webservice.entity.ObjectFactory;
import com.skynet.spms.webservice.entity.QueryLocationOutParameters;
import com.skynet.spms.webservice.entity.QueryLocationParameters;
import com.skynet.spms.webservice.entity.QueryTasklistByTAGInputParameters;
import com.skynet.spms.webservice.entity.QueryTasklistInputParameters;
import com.skynet.spms.webservice.entity.QueryUserInputParameters;
import com.skynet.spms.webservice.entity.SetTaskDetailsExeInputParameters;
import com.skynet.spms.webservice.entity.TasklistReocrdsOutputParameters;
import com.skynet.spms.webservice.entity.UserIDCardInputParameters;
import com.skynet.spms.webservice.entity.UserInfoInputParameters;
import com.skynet.spms.webservice.entity.UserInfoOutputParameters;
import com.skynet.spms.webservice.entity.VisualLocationInputParameters;
import com.skynet.spms.webservice.entity.VisualLocationOutputParameters;

/**
 * This class was generated by Apache CXF 2.3.1
 * Fri May 27 14:45:04 CST 2011
 * Generated source version: 2.3.1
 * 
 */
 
@WebService(targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", name = "RFIDPortTypeSOAP")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface RFIDPortTypeSOAP {

    @WebResult(name = "QueryLocationOutParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public QueryLocationOutParameters getLocationInfo(
        @WebParam(partName = "request", name = "QueryLocationParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        QueryLocationParameters request
    ) throws FaultResponse;

    @WebResult(name = "AllUsersOutParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public AllUsersOutParameters getUserIDCard(
        @WebParam(partName = "request", name = "QueryUserIDCardInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        QueryUserInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "GetRFIDSerialOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public GetRFIDSerialOutputParameters getRFIDSerial(
        @WebParam(partName = "request", name = "GetRFIDSerialInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        GetRFIDSerialInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "VisualLocationOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public VisualLocationOutputParameters getVirtualLocaltion(
        @WebParam(partName = "request", name = "VisualLocationInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        VisualLocationInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "TasklistReocrdsOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public TasklistReocrdsOutputParameters getTasklist(
        @WebParam(partName = "request", name = "QueryTasklistInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        QueryTasklistInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "ResultOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public boolean setUserIDCard(
        @WebParam(partName = "request", name = "UserIDCardInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        UserIDCardInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "ResultOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public boolean check4DoorControl(
        @WebParam(partName = "request", name = "Check4DoorControlInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        Check4DoorControlInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "TasklistReocrdsOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public TasklistReocrdsOutputParameters getRFIDTagTask(
        @WebParam(partName = "request", name = "QueryTasklistByTAGInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        QueryTasklistByTAGInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "ResultOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public boolean setTaskDetailsExe(
        @WebParam(partName = "request", name = "SetTaskDetailsExeInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        SetTaskDetailsExeInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "IsEnableOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public boolean isUserEnable(
        @WebParam(partName = "request", name = "QueryUserInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        QueryUserInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "AllUsersOutParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public AllUsersOutParameters getAllUsers(
        @WebParam(partName = "request", name = "AllUsersInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        AllUsersInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "UserInfoOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public UserInfoOutputParameters getUserInfo(
        @WebParam(partName = "request", name = "UserInfoInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        UserInfoInputParameters request
    ) throws FaultResponse;

    @WebResult(name = "GetTaskDetailsOutputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl", partName = "response")
    @WebMethod
    public GetTaskDetailsOutputParameters getTaskDetails(
        @WebParam(partName = "request", name = "GetTaskDetailsInputParameters", targetNamespace = "http://www.skynetsoft.org/RFIDwsdl")
        GetTaskDetailsInputParameters request
    ) throws FaultResponse;
}