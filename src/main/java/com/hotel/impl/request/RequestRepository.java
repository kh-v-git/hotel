package com.hotel.impl.request;

import java.util.List;

public interface RequestRepository {
    List<Request> getRequestList(String searchText, List<String> searchParametersList);

    List<Request> getRequestList(int userId);

    boolean setRequest(Request request);
    boolean updateRequest (int id, int userId, String status);

    Request getRequest(int id);

    boolean deleteRequest(int requestId);

    boolean deleteRequest(int requestId, int userId);


    boolean updateRequest(Request request);
}
