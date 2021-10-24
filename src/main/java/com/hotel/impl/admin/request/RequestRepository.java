package com.hotel.impl.admin.request;

import java.util.List;

public interface RequestRepository {
    List<Request> getRequestList(String searchText, List<String> searchParametersList);

    boolean setRequest(Request request);

    Request getRequest(int id);

    boolean deleteRequest(int id);

    boolean updateRequest(Request request);
}
