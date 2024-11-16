package com.theprojectchow.backend.profile.domain.model.queries.buyer;


import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;

public record GetBuyerByEmailQuery(EmailAddress emailAddress) {
}
