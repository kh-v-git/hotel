package com.hotel.impl.request;

import java.util.List;

public class RequestService {
    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getRequestList(String searchText, List<String> searchParametersList) {
        return requestRepository.getRequestList(searchText, searchParametersList);
    }

    public List<Request> getRequestList(int userId) {
        return requestRepository.getRequestList(userId);
    }

    public boolean setRequest(Request request) {
        return requestRepository.setRequest(request);
    }

    public boolean updateRequest (int id, int userId, String status) {
        return requestRepository.updateRequest(id, userId, status);
    }

    public boolean deleteRequest(int id) {
        return requestRepository.deleteRequest(id);
    }

    public boolean deleteRequest(int requestId, int userId) {
        return requestRepository.deleteRequest(requestId, userId);
    }

    public boolean updateRequest(Request request) {
        return requestRepository.updateRequest(request);
    }

    public Request getRequest(int id) {
        return requestRepository.getRequest(id);
    }
}
