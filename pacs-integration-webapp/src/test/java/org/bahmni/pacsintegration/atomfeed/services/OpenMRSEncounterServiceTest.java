package org.bahmni.pacsintegration.atomfeed.services;

import org.bahmni.pacsintegration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.pacsintegration.atomfeed.mappers.OpenMRSEncounterToOrderMapper;
import org.bahmni.pacsintegration.model.Orders;
import org.bahmni.pacsintegration.model.OrderType;
import org.bahmni.pacsintegration.repository.OrderRepository;
import org.bahmni.pacsintegration.repository.OrderTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class OpenMRSEncounterServiceTest {
    @Mock
    OrderTypeRepository orderTypeRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OpenMRSEncounterToOrderMapper openMRSEncounterToOrderMapper;

    @InjectMocks
    OpenMRSEncounterService openMRSEncounterService = new OpenMRSEncounterService();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldMapOpenMRSEncounterIntoOrdersAndSave() throws Exception {
        OpenMRSEncounter openMRSEncounter = new OpenMRSEncounter();
        ArrayList<Orders> listOfOrderses = new ArrayList<Orders>();
        ArrayList<OrderType> acceptableOrderTypes = new ArrayList<OrderType>();
        when(openMRSEncounterToOrderMapper.map(openMRSEncounter, acceptableOrderTypes, orderRepository)).thenReturn(listOfOrderses);

        openMRSEncounterService.save(openMRSEncounter);

        verify(openMRSEncounterToOrderMapper, timeout(1)).map(openMRSEncounter, acceptableOrderTypes, orderRepository);
        verify(orderRepository, times(1)).save(listOfOrderses);
    }
}