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
		if (!checkOut.after(checkIn)) { //programação defenciva// propagada
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

	public Date getCheckIn() { //não há set// updateDates fará a atualização
		return checkIn;
	}

	public Date getCheckOut() { //não há set// updateDates fará a atualização
		return checkOut;
	}

	public long duration() { //duração em dias// duração retorna do tipo 'long' (inteiro mais longo)
		long diff = checkOut.getTime() - checkIn.getTime(); //cálcular a diferença entre datas em mile segundos
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
			+ duration() //método duration (cálculo dos dias)
			+ " nights";
	}
}
