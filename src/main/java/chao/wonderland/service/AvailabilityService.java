package chao.wonderland.service;

import chao.wonderland.bo.Availability;
import chao.wonderland.repository.AvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getAvailabilities(String startDate, String endDate){
        return availabilityRepository.findAllAvailabilitiesInDateRange(startDate, endDate);
    }
}
