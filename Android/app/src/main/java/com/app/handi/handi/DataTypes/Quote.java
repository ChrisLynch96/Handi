package com.app.handi.handi.DataTypes;

import java.io.Serializable;

/**
 * Created by Killian on 01/04/2017.
 */

public class Quote implements Serializable{

    private String amount;
    private String userUid;
    private String HandiUid;
    private String jobId;
    private String HandiName;
    private Boolean isqAccepted;

    public Quote(){}
    public Quote(String amount,String userUid,String HandiUid,String jobId,Boolean isqAccepted,String HandiName){
        this.amount = amount;
        this.userUid = userUid;
        this.HandiUid = HandiUid;
        this.jobId = jobId;
        this.isqAccepted = isqAccepted;
        this.HandiName = HandiName;
    }

    public String getHandiName() {
        return HandiName;
    }

    public void setHandiName(String handiName) {
        HandiName = handiName;
    }

    public Boolean getIsqAccepted() {
        return isqAccepted;
    }

    public void setIsqAccepted(Boolean isqAccepted) {
        this.isqAccepted = isqAccepted;
    }

    public String getHandiUid() {
        return HandiUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public void setHandiUid(String handiUid) {
        HandiUid = handiUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public String getAmount() {
        return amount;
    }

    public String getJobId() {
        return jobId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
