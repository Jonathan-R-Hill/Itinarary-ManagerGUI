package Objects;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class Activity {

    private final String activityName;
    private final String activityCode;
    private final String baseCost;
    private final String location;
    private final String expectedDuration;
    private final String startTime;
    private final String generalDescription;

    public Activity(String activityName, String activityCode, String baseCost, String location, String expectedDuration, String startTime, String generalDescription) {
        this.activityName = activityName;
        this.activityCode = activityCode;
        this.baseCost = baseCost;
        this.location = location;
        this.expectedDuration = expectedDuration;
        this.startTime = startTime;
        this.generalDescription = generalDescription;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public String getBaseCost() {
        return baseCost;
    }

    public String getLocation() {
        return location;
    }

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getGeneralDescription() {
        return generalDescription;
    }
}
