package calendar;

public class Date {

	// attributes etc.

	public Date(int dd,int mm,int yyyy) throws IllegalDateException{
		if(!isValidDate(dd, mm, yyyy)) {
			throw new IllegalDateException();
		}
		this.mm=mm;
		this.dd=dd;
		this.yyyy=yyyy;
	}

	// Returns the zodiac sign 
	public String zodiacSign(){
		if ((getMm() == 12 && getDd() >= 22 && getDd() <= 31) || (getMm() == 1 && getDd() >= 1 && getDd() <= 19))
			return "Capricorn";
		else if ((getMm() ==  1 && getDd() >= 20 && getDd() <= 31) || (getMm() == 2 && getDd() >= 1 && getDd() <= 18))
			return "Aquarius";
		else if ((getMm() ==  2 && getDd() >= 19 && getDd() <= 29) || (getMm() == 3 && getDd() >= 1 && getDd() <= 20))
			return "Pisces";
		else if ((getMm() ==  3 && getDd() >= 20 && getDd() <= 31) || (getMm() == 4 && getDd() >= 1 && getDd() <= 19))
			return "Aries";
		else if ((getMm() ==  4 && getDd() >= 20 && getDd() <= 30) || (getMm() == 5 && getDd() >= 1 && getDd() <= 20))
			return "Taurus";
		else if ((getMm() ==  5 && getDd() >= 21 && getDd() <= 31) || (getMm() == 6 && getDd() >= 1 && getDd() <= 20))
			return "Gemini";
		else if ((getMm() ==  6 && getDd() >= 21 && getDd() <= 30) || (getMm() == 7 && getDd() >= 1 && getDd() <= 22))
			return "Cancer";
		else if ((getMm() ==  7 && getDd() >= 23 && getDd() <= 31) || (getMm() == 8 && getDd() >= 1 && getDd() <= 22))
			return "Leo";
		else if ((getMm() ==  8 && getDd() >= 23 && getDd() <= 31) || (getMm() == 9 && getDd() >= 1 && getDd() <= 22))
			return "Virgo";
		else if ((getMm() ==  9 && getDd() >= 23 && getDd() <= 30) || (getMm() == 10 && getDd() >= 1 && getDd() <= 22))
			return "Libra";
		else if ((getMm() == 10 && getDd() >= 23 && getDd() <= 31) || (getMm() == 11 && getDd() >= 1 && getDd() <= 21))
			return "Scorpio";
		else if ((getMm() == 11 && getDd() >= 22 && getDd() <= 30) || (getMm() == 12 && getDd() >= 1 && getDd() <= 21))
			return "Sagittarius";
		else
			return null;
	}

	// the rest of the methods


}
