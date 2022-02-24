package com.mergen.vtys.vtysdatabaseap.Service.Impl;

import com.mergen.vtys.vtysdatabaseap.Model.Activity;
import com.mergen.vtys.vtysdatabaseap.Model.User;
import com.mergen.vtys.vtysdatabaseap.Repository.ActivityRepository;
import com.mergen.vtys.vtysdatabaseap.Service.ActivityService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    @Override
    public List<Activity> getActivityList() {
        return (List<Activity>) activityRepository.findAll();
    }

    @Override
    public Optional<Activity> getActivityById(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            return activity;
        } else
            throw new IllegalArgumentException(id + "id search error");
    }

    @Override
    public Optional<Activity> getActivityByName(String name){
        Optional<Activity> activity = activityRepository.findByName(name);
        if(activity.isPresent()){
            return activity;
    }
        else
            throw new IllegalArgumentException(name + "internal server error");


}
    @Override
    public Activity Create(Activity model) {
        Optional<Activity> _activity = activityRepository.findByName(model.getName());
        if(!_activity.isPresent()){
            activityRepository.save(model);
            return model;
        }
        else
            throw new IllegalArgumentException("internal server error");

    }



    @Override
    public String Update(Long id, Activity model) {
        Optional<Activity> _activity = activityRepository.findById(id);
        if(_activity.isPresent()){
            activityRepository.save(model);
            return model.getName();}
       else
           throw new IllegalArgumentException();
    }

    @Override
    public String Delete(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);
        if(activity.isPresent()){
            activityRepository.deleteById(id);
            return id.toString();}
        else
        throw  new IllegalArgumentException();
    }
}
