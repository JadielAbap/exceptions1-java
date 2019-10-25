package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) { //programa��o defenciva// propagada
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() { //n�o h� set// updateDates far� a atualiza��o
		return checkIn;
	}

	public Date getCheckOut() { //n�o h� set// updateDates far� a atualiza��o
		return checkOut;
	}

	public long duration() { //dura��o em dias// dura��o retorna do tipo 'long' (inteiro mais longo)
		long diff = checkOut.getTime() - checkIn.getTime(); //c�lcular a diferen�a entre datas em mile segundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //converter mile segundos para dias
	}
	
	public void updateDates(Date checkIn, Date checkOut) { //receber as duas datas novas// atualizar checkIn e checkOut
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() { 
		return "Room "
			+ roomNumber
			+ ", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out: "
			+ sdf.format(checkOut)
			+ ", "
			+ duration() //m�todo duration (c�lculo dos dias)
			+ " nights";
	}
}
