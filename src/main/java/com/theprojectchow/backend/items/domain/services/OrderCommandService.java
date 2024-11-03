package com.theprojectchow.backend.items.domain.services;

import com.theprojectchow.backend.items.domain.model.commands.CancelOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.ConfirmOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.CreateOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.RejectOrderCommand;

public interface OrderCommandService {
    Long handle(ConfirmOrderCommand command);
    Long handle(RejectOrderCommand command);
    Long handle(CancelOrderCommand command);
    Long handle(CreateOrderCommand command);
}
