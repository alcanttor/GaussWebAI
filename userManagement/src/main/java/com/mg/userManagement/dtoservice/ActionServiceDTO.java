package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.ActionDTO;
import com.mg.userManagement.entity.Action;
import com.mg.userManagement.service.ActionService;

@Service
public class ActionServiceDTO {

	@Autowired
	private ActionService actionService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ActionDTO saveAction(ActionDTO actionDTO)
	{
		Action action = modelMapper.map(actionDTO, Action.class);
		return modelMapper.map(actionService.save(action), ActionDTO.class);
	}
	
	public List<ActionDTO> getAllActions()
	{
		@SuppressWarnings("serial")
		Type listActions = new TypeToken<ActionDTO>() {}.getType();
		return modelMapper.map(actionService.getAll(), listActions);
	}
}
