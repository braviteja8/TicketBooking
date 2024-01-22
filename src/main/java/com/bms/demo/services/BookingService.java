package com.bms.demo.services;

import com.bms.demo.dtos.BookShowRequestDto;
import com.bms.demo.exceptions.SeatNotAvailable;
import com.bms.demo.exceptions.ShowNotFound;
import com.bms.demo.exceptions.UserIsNotValid;
import com.bms.demo.models.*;
import com.bms.demo.repositories.BookingRepository;
import com.bms.demo.repositories.ShowRepository;
import com.bms.demo.repositories.ShowSeatRepository;
import com.bms.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;


    // @Transactional(isolation= Isolation.SERIALIZABLE)
    public Booking bookShow(BookShowRequestDto request) throws UserIsNotValid, ShowNotFound, SeatNotAvailable {

        //get user with userId;
        Optional<User> user = userRepository.findById(request.getUserId());
        if (!user.isPresent()) {
            throw new UserIsNotValid();
        }
        //get show with show_id;
        Optional<Show> show = showRepository.findById(request.getShowId());
        if (!show.isPresent()) {
            throw new ShowNotFound();
        }

        List<ShowSeat> reserveShowSeats = reserveShowSeats(request.getShowSeatIds());
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculator(request.getShowSeatIds(), request.getShowId()));
        booking.setUser(user.get());
        booking.setShowSeatList(reserveShowSeats);
        //we arent handling this right now;
        booking.setPayments(new ArrayList<>());
        return bookingRepository.save(booking);

    }

    private int priceCalculator(List<Long> showSeatIds, Long showId) {
        //get the show
        //get the seats
        //for the seat_ids,you can find the seat type
        //for the pair(showId,seatType)->price
        //sum it up for all seats selected;
        return 0;
    }

    //   anotations will come only if method is public.
    // @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveShowSeats(List<Long> showSeatIds) throws SeatNotAvailable {
        // get List<showSeat> for showSeatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
//checking if any of the showSeats are already reserved->throw an error.
        for (ShowSeat showSeat : showSeats) {
            if (!ShowSeatStatus.AVAILABLE.equals(showSeat.getShowSeatStatus()) ||
                    ShowSeatStatus.LOCKED.equals(showSeat.getShowSeatStatus()) && Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes() < 10)
                throw new SeatNotAvailable();
        }
        //here we do locking,only if
        //1.all the seats are available.
        //2.if all the seats are locked and lockedDuration>10

        List<ShowSeat> reservedShowSeats = new ArrayList<>();
        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            reservedShowSeats.add(showSeatRepository.save(showSeat));
        }
        return reservedShowSeats;
    }
}
