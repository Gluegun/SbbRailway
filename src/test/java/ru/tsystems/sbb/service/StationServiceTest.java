package ru.tsystems.sbb.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.mapper.ScheduleMapper;
import ru.tsystems.school.mapper.StationMapper;
import ru.tsystems.school.mapper.TrainMapper;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.service.impl.StationServiceImpl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StationServiceTest {

    @Mock
    private StationDao stationDao;
    @Mock
    private TrainDao trainDao;
    @Mock
    private StationMapper stationMapper;
    @Mock
    private TrainMapper trainMapper;
    @Mock
    private ScheduleMapper scheduleMapper;
    @Mock
    private JmsTemplate jmsTemplate;
    @InjectMocks
    private StationServiceImpl stationService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateStation() {

        Station station = new Station("stationName");

        StationDto stationDto = new StationDto("stationName");
        stationDto.setId(1);

        when(stationDao.findById(anyInt())).thenReturn(station);

        stationService.update(0, stationDto);

        verify(stationDao, times(1)).findById(eq(0));
        verifyNoInteractions(trainDao);
        verifyNoInteractions(jmsTemplate);
        verifyNoInteractions(trainMapper);
        verifyNoInteractions(scheduleMapper);

    }
}
