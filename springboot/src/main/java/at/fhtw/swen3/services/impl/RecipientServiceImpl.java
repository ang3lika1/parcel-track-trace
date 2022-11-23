package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.RecipientService;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecipientServiceImpl implements RecipientService {
    private final RecipientMapper recipientMapper;
    private final Validator validator;
    private final RecipientRepository recipientRepository;
}
