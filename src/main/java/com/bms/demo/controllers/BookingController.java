package com.bms.demo.controllers;
import java.util.*;
import com.bms.demo.dtos.BookShowRequestDto;
import com.bms.demo.dtos.BookShowResponseDto;

import com.bms.demo.dtos.ResponseStatus;
import com.bms.demo.exceptions.SeatNotAvailable;
import com.bms.demo.exceptions.ShowNotFound;
import com.bms.demo.exceptions.UserIsNotValid;
import com.bms.demo.models.Booking;
import com.bms.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private BookingService bookingService;
    private static final String  USER_INVALID_MESSAGE="User id is not valid";
    private static final String SHOW_INVALID_MESSAGE="show id valid";
    private static final String SOMETHING_WENT_WRONG="something went wrong";
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookShowResponseDto bookShow(BookShowRequestDto request) {
        try {
            Booking booking = bookingService.bookShow(request);
            return new BookShowResponseDto(booking.getId(), booking.getAmount(), ResponseStatus.SUCCESS, "SUCCESS");
        } catch (UserIsNotValid e) {
            System.out.println("not booked");
            return new BookShowResponseDto(null, 0, ResponseStatus.FAILURE, USER_INVALID_MESSAGE);
        } catch (ShowNotFound e) {
            return new BookShowResponseDto(null, 0, ResponseStatus.FAILURE, SHOW_INVALID_MESSAGE);
        } catch (SeatNotAvailable e) {
            return new BookShowResponseDto(null, 0, ResponseStatus.FAILURE, SOMETHING_WENT_WRONG);
        }


    }
}
