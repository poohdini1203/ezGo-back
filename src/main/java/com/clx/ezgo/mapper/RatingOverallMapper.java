package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.RatingOverall;

public interface RatingOverallMapper {
    public void add(RatingOverall ratingOverall);
    public void update(RatingOverall ratingOverall);
    public RatingOverall get(String driverID);


}
