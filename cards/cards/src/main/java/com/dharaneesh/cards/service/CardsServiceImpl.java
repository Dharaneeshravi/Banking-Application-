package com.dharaneesh.cards.service;

import com.dharaneesh.cards.constants.CardsConstants;
import com.dharaneesh.cards.dto.CardsDto;
import com.dharaneesh.cards.exception.CardAlreadyExistException;
import com.dharaneesh.cards.exception.ResourceNotFoundException;
import com.dharaneesh.cards.mapper.CardsMapper;
import com.dharaneesh.cards.model.Cards;
import com.dharaneesh.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     *
     * @param mobileNumber
     */
    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> optionalCards=cardsRepository.findByMobileNumber(mobileNumber);

        if(optionalCards.isPresent())
        {
            throw new CardAlreadyExistException("Card already exist with the given mobile number : "+mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));

    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CardsDto fetchCards(String mobileNumber) {

        Cards cards=cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Card","mobileNumber",mobileNumber));

        return CardsMapper.mapCardsToDto(cards,new CardsDto());
    }

    /**
     *
     * @param cardsDto
     * @return
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Cards cards=cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",cardsDto.getCardNumber()));
        CardsMapper.mapDtoToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }


    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Card","mobileNumber",mobileNumber));
        cardsRepository.delete(cards);
        return true;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    private Cards createNewCard(String mobileNumber)
    {
        Cards newCard=new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

}
