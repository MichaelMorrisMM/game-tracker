package com.michaelmorris.gametracker.model;

import com.michaelmorris.gametracker.exceptions.InvalidFilterException;
import com.michaelmorris.gametracker.service.PlatformService;
import com.michaelmorris.gametracker.service.PriorityService;
import com.michaelmorris.gametracker.service.StatusService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameProfileFilter {

    private final PriorityService priorityService;
    private final StatusService statusService;
    private final PlatformService platformService;

    private boolean filteringByPriority = false;
    private List<Priority> priorities;

    private boolean filteringByStatus = false;
    private List<Status> statuses;

    private boolean filteringByPlatform = false;
    private Platform platform;

    public GameProfileFilter(PriorityService priorityService, StatusService statusService, PlatformService platformService) {
        this.priorityService = priorityService;
        this.statusService = statusService;
        this.platformService = platformService;

        this.priorities = this.priorityService.getAllPriorities();
        this.statuses = this.statusService.getAllStatuses();
    }

    public void addFiltering(String field, String value) throws InvalidFilterException {
        switch (field) {
            case "priority":
                if (!this.filteringByPriority) {
                    this.filteringByPriority = true;
                    this.priorities = new ArrayList<>();
                }
                this.priorities.add(this.priorityService.getPriority(value).orElseThrow((() -> new InvalidFilterException("No priority found with name " + value))));
                break;
            case "status":
                if (!this.filteringByStatus) {
                    this.filteringByStatus = true;
                    this.statuses = new ArrayList<>();
                }
                this.statuses.add(this.statusService.getStatus(value).orElseThrow(() -> new InvalidFilterException("No status found with name " + value)));
                break;
            case "platform":
                if (this.filteringByPlatform) {
                    throw new InvalidFilterException("Unable to filter by more than one platform");
                }
                this.filteringByPlatform = true;
                this.platform = this.platformService.getPlatform(value).orElseThrow(() -> new InvalidFilterException("No platform found with name " + value));
                break;
            default:
                throw new InvalidFilterException("Unsupported filter field " + field);
        }
    }

}
