package com.hotel.impl.admin.request;

import com.hotel.impl.admin.order.Order;

import java.util.List;

public class RequestService {
    private RequestRepository requestRepository;
    public RequestService (RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
    public List<Request> getRequestList(String searchText) {
        return requestRepository.getRequestList(searchText);
    }

    public boolean setRequest(Request request) {
        return requestRepository.setRequest(request);
    }

    public boolean deleteRequest(int id) {
        return requestRepository.deleteRequest(id);
    }

    public boolean updateRequest(Request request) {
        return requestRepository.updateRequest(request);
    }

    public Request getRequest(int id) {
        return requestRepository.getRequest(id);
    }
}
