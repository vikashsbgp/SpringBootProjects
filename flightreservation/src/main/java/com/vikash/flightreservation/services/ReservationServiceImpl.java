package com.vikash.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.flightreservation.dto.ReservationRequest;
import com.vikash.flightreservation.entities.Flight;
import com.vikash.flightreservation.entities.Passenger;
import com.vikash.flightreservation.entities.Reservation;
import com.vikash.flightreservation.repos.FlightRepository;
import com.vikash.flightreservation.repos.PassengerRepository;
import com.vikash.flightreservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setId(flightId);
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setMiddleName(request.getPassengerMiddleName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setId(flightId);
		reservation.setCheckedIn(true);
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		Reservation savedReservation = reservationRepository.save(reservation);
		
		return savedReservation;
	}

}
