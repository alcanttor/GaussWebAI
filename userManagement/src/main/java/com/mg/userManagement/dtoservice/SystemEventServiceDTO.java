package com.mg.userManagement.dtoservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.dto.SystemEventDTO;
import com.mg.userManagement.entity.SystemEvent;
import com.mg.userManagement.service.SystemEventService;

@Service
public class SystemEventServiceDTO {

	@Autowired
	private SystemEventService systemEventService; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SystemEventDTO saveEvent(SystemEventDTO systemEventDTO) {
		SystemEvent systemEvent = modelMapper.map(systemEventDTO, SystemEvent.class);
		System.out.println("save this event : "+systemEvent);
		return modelMapper.map(systemEventService.save(systemEvent), SystemEventDTO.class);
	}
}
