package hanafuda.server;

import java.util.HashMap;

public enum Card {
	Null (-2, -2),
	FaceDown(-1, -1),
	Pine1 (0, 0), 
	Pine2 (0, 1), 
	PineRibbon (0, 2), 
	PineBright (0, 3), 
	Plum1 (1, 4), 
	Plum2 (1, 5), 
	PlumRibbon (1, 6), 
	PlumAnimal (1, 7),  
	Sakura1 (2, 8), 
	Sakura2 (2, 9), 
	SakuraRibbon(2, 10), 
	SakuraBright(2, 11), 
	Wisteria1 (3, 12), 
	Wisteria2 (3, 13), 
	WisteriaRibbon (3, 14), 
	WisteriaAnimal (3, 15), 
	Iris1 (4, 16), 
	Iris2 (4, 17), 
	IrisRibbon (4, 18), 
	IrisAnimal (4, 19), 
	Rose1 (5, 20), 
	Rose2 (5, 21), 
	RoseRibbon (5, 22), 
	RoseAnimal (5, 23), 
	Clover1 (6, 24), 
	Clover2 (6, 25), 
	CloverRibbon (6, 26), 
	CloverAnimal (6, 27), 
	Plains1 (7, 28), 
	Plains2 (7, 29), 
	PlainsAnimal (7, 30), 
	PlainsBright (7, 31), 
	Chrys1 (8, 32), 
	Chrys2 (8, 33), 
	ChrysRibbon (8, 34), 
	ChrysAnimal (8, 35), 
	Maple1 (9, 36), 
	Maple2 (9, 37), 
	MapleRibbon (9, 38), 
	MapleAnimal (9, 39), 
	Willow1 (10, 40), 
	WillowRibbon (10, 41), 
	WillowAnimal (10, 42), 
	WillowBright (10, 43), 
	Star1 (11, 44), 
	Star2 (11, 45), 
	Star3 (11, 46), 
	StarBright (11, 47);
	
	public byte suit,  cardID;
	protected static HashMap<Byte, Card> byID = new HashMap<Byte, Card>();
	
	private Card(int suit, int cardID) {
		this.suit = (byte) suit;
		this.cardID = (byte) cardID;
	}
	
	static Card blindCard(Card c) {
		if (c == Null) return c;
		else return FaceDown;
	}
	
	static void initialize() {
		for (Card c : Card.values()) byID.put(c.cardID, c);
	}
	
	static boolean combos(Card c1, Card c2) {
		return (c1.suit == c2.suit) && c2 != null;
	}
	
	static Card getByID(byte index) {
		return byID.get(index);
	}
	
}
