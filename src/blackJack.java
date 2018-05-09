// Author:		Jeremy Gavisk
// Assignment:	Final Project: BlackJack

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Class for BlackJack Game in JavaFX
public class blackJack extends Application{
	
	// variables for dealer and user points.
	int userPoints = 0;
	int dealerPoints = 0;
	// variables for user wins and losses.
	int userWins = 0;
	int userLosses = 0;
	
	// variable to hold dealer's first card.
	String dealerFirstCard ="";
	
	// variables to count number of cards dealt and the index of the randomly drawn card.
	int cardIndex = 0;
	int cardsLeft = 52;
	
	// variables to use with game and dealer logic.
	boolean valid = true;	
	boolean isActive = true;
	boolean needBreak = false;
	
	// array of card names to reference images.
	String[] cardNames = new String[]{
			"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "cj", "cq", "ck", 
			"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10", "dj", "dq", "dk",
			"h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10", "hj", "hq", "hk",
			"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "sj", "sq", "sk"
	};
	
	// parallel array to cardNames for point values.
	int[] cardValues = new int[]{
			11,2,3,4,5,6,7,8,9,10,10,10,10,
			11,2,3,4,5,6,7,8,9,10,10,10,10,
			11,2,3,4,5,6,7,8,9,10,10,10,10,
			11,2,3,4,5,6,7,8,9,10,10,10,10
	};
	
	// array to be used to hold dealt cards and to be used with Validate method.
	ArrayList<String> dealtCards = new ArrayList<String>();
		
	// buttons for application interface.
	Button btHitMe = new Button("Hit Me");
	Button btStay = new Button("Stay");
	Button btDeal = new Button("Deal Cards");
	Button btShuffle = new Button("Shuffle Deck");
	
	// labels for application interface.
	Label lblDealer = new Label("Dealer: ");
	Label lblUser = new Label("User: ");
	Label lblScore = new Label("Wins / Losses: 0 / 0");
	Label lblWL = new Label(Integer.toString(userWins) + " / " + Integer.toString(userLosses));
	Label lblCardsLeftInDeck = new Label("Cards Left: " + cardsLeft);
	Label lblActionField = new Label("Click Deal Cards to Start");
	
	// VBoxes to hold buttons.
	VBox vBoxButtons = new VBox(5);
	VBox vBoxDealShuffle = new VBox(5);
	
	// Imageview variables to hold dealer's and user's card images.
	ImageView dealerCard1 = new ImageView(new Image(blackJack.class.getResourceAsStream("/images/b1fv.png")));
	ImageView dealerCard2 = new ImageView(new Image(blackJack.class.getResourceAsStream("/images/b1fv.png")));
	ImageView dealerCard3 = new ImageView();
	ImageView dealerCard4 = new ImageView();
	ImageView dealerCard5 = new ImageView();
	ImageView dealerCard6 = new ImageView();
	ImageView dealerCard7 = new ImageView();
	ImageView dealerCard8 = new ImageView();
	ImageView userCard1 = new ImageView(new Image(blackJack.class.getResourceAsStream("/images/b1fv.png")));
	ImageView userCard2 = new ImageView(new Image(blackJack.class.getResourceAsStream("/images/b1fv.png")));
	ImageView userCard3 = new ImageView();
	ImageView userCard4 = new ImageView();
	ImageView userCard5 = new ImageView();
	ImageView userCard6 = new ImageView();
	ImageView userCard7 = new ImageView();
	ImageView userCard8 = new ImageView();
	
	GridPane grid = new GridPane();	
	
	@Override // Override the start method in the Application class.
	public void start(Stage primaryStage) {
		
		// set VBoxes on stage
		vBoxButtons.getChildren().addAll(btHitMe, btStay);
		vBoxButtons.setAlignment(Pos.TOP_LEFT);
		vBoxDealShuffle.getChildren().addAll(btDeal, btShuffle);
		vBoxDealShuffle.setAlignment(Pos.TOP_LEFT);
		
		// deactivate HitMe and Stay buttons until the game has started.
		btHitMe.setDisable(true);
		btStay.setDisable(true);
		
		// setting grid pane and children.
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(5,5,5,5)); 
		grid.add(lblDealer, 1, 0);
		grid.add(dealerCard1, 1, 1);
		grid.add(dealerCard2, 2, 1);
		grid.add(dealerCard3, 3, 1);
		grid.add(dealerCard4, 4, 1);
		grid.add(dealerCard5, 5, 1);
		grid.add(dealerCard6, 6, 1);
		grid.add(dealerCard7, 7, 1);
		grid.add(dealerCard8, 8, 1);
		grid.add(lblUser, 1, 2);
		grid.add(userCard1, 1, 3);
		grid.add(userCard2, 2, 3);
		grid.add(userCard3, 3, 3);
		grid.add(userCard4, 4, 3);
		grid.add(userCard5, 5, 3);
		grid.add(userCard6, 6, 3);
		grid.add(userCard7, 7, 3);
		grid.add(userCard8, 8, 3);
		grid.add(lblActionField, 9, 0);
		grid.add(vBoxDealShuffle, 9, 1);
		grid.add(vBoxButtons,9,3);
		grid.add(lblScore, 9, 5);
		grid.add(lblCardsLeftInDeck,9,6);
		
		// Eventhandler for when the Deal Card button is clicked.
		btDeal.setOnAction( e -> {
			btDeal.setDisable(true);
			btShuffle.setDisable(true);
			btHitMe.setDisable(false);
			btStay.setDisable(false);
			// Alert in case the number of cards left in the deck drops below 15.
			if(cardsLeft < 15){
				Alert shuffleAlert = new Alert(AlertType.INFORMATION, "Numbers of cards left below 15. Please consider reshuffling deck.");
				shuffleAlert.showAndWait();
			}
			DealCards();
		});
		
		// Eventhandler for when the Stay button is clicked.
		btStay.setOnAction( e -> {
			dealerCard1.setImage(new Image("/images/" + dealerFirstCard + ".png"));
			if(dealerPoints >= userPoints){
				Loss();
			} else if(dealerPoints >= 17){
				Win();
			} else{
					do{
						isActive = true;
						if (dealerCard3.getImage() == null){
						RandomCardDealerHit();
						dealerPoints = dealerPoints + cardValues[cardIndex];
						dealerCard3.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));
						CheckPointsDealer();
						isActive = false;
						if(needBreak) break;
						} else if (dealerCard4.getImage() == null){
							if (dealerPoints < 17){
							RandomCardDealerHit();
							dealerPoints = dealerPoints + cardValues[cardIndex];
							dealerCard4.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
						    CheckPointsDealer();
						    if(needBreak) break;
						    isActive = false;
						    } else{CheckPointsDealer();}
						} else if(dealerCard5.getImage() == null){
							if (dealerPoints < 17){
							RandomCardDealerHit();
							dealerPoints = dealerPoints + cardValues[cardIndex];
							dealerCard5.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
						    CheckPointsDealer();
						    if(needBreak) break;
						    isActive = false;
						    } else{CheckPointsDealer();}
						} else if(dealerCard6.getImage() == null){
							if (dealerPoints < 17){
							RandomCardDealerHit();
							dealerPoints = dealerPoints + cardValues[cardIndex];
							dealerCard6.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
						    CheckPointsDealer();
						    if(needBreak) break;
						    isActive = false;
						    } else{CheckPointsDealer();}
						} else if(dealerCard7.getImage() == null){
							if (dealerPoints < 17){
							RandomCardDealerHit();
							dealerPoints = dealerPoints + cardValues[cardIndex];
							dealerCard7.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
						    CheckPointsDealer();
						    if(needBreak) break;
						    isActive = false;
						    } else{CheckPointsDealer();}
						} else {
							if(dealerPoints < 17){
							RandomCardDealerHit();
							dealerPoints = dealerPoints + cardValues[cardIndex];
							dealerCard8.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
						    CheckPointsDealer();
						    if(needBreak) break;
						    isActive = false;
						    } else{CheckPointsDealer();}
						}
					} while(!isActive);
				}	
		});

		// Eventhandler for when the Hit Me button is clicked.
		btHitMe.setOnAction( e -> {
			if (userCard3.getImage() == null){
			RandomCard();
			userPoints = userPoints + cardValues[cardIndex];
			userCard3.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));
			CheckPoints();
			} else if (userCard4.getImage() == null){
				RandomCard();
				userPoints = userPoints + cardValues[cardIndex];
				userCard4.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
			    CheckPoints();
			} else if(userCard5.getImage() == null){
				RandomCard();
				userPoints = userPoints + cardValues[cardIndex];
				userCard5.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
			    CheckPoints();
			} else if(userCard6.getImage() == null){
				RandomCard();
				userPoints = userPoints + cardValues[cardIndex];
				userCard6.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
			    CheckPoints();
			} else if(userCard7.getImage() == null){
				RandomCard();
				userPoints = userPoints + cardValues[cardIndex];
				userCard7.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
			    CheckPoints();
			} else {
				RandomCard();
				userPoints = userPoints + cardValues[cardIndex];
				userCard8.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));	
			    CheckPoints();
				btHitMe.setDisable(true);
			}			
		});

		// Eventhandler for when the Shuffle Deck button is clicked.
		btShuffle.setOnAction( e -> {
			Shuffle();
		});
		
		// set scene on stage
		Scene scene = new Scene(grid, 650, 400);
		primaryStage.setTitle("BlackJack");
		primaryStage.setScene(scene); 
		primaryStage.show();
	}
	
	// method for dealing the first four cards in the new hand.
	public void DealCards(){
		ResetHands();
		lblActionField.setText("Good Luck!");
		RandomCardDealer();
		dealerPoints = dealerPoints + cardValues[cardIndex];
		dealerFirstCard = cardNames[cardIndex];
		RandomCardDealer();
		dealerPoints = dealerPoints + cardValues[cardIndex];
		dealerCard2.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));
		RandomCard();
		userPoints = userPoints + cardValues[cardIndex];
		userCard1.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));
		RandomCard();
		userPoints = userPoints + cardValues[cardIndex];
		userCard2.setImage(new Image("/images/" + cardNames[cardIndex] + ".png"));
	}
	
	// method for randomly drawing a card from the remaining cards in the deck.
	public void RandomCard(){
		cardIndex = 0 + (int) (Math.random() * ((cardNames.length-1) + 1));
		Validate();
		dealtCards.add(cardNames[cardIndex]);
		// Alert to ask user if they would like to hae the ace be worth 11 or 1. The default value is 11.
		if (cardNames[cardIndex].equals("s1") || cardNames[cardIndex].equals("d1") || cardNames[cardIndex].equals("h1") || cardNames[cardIndex].equals("c1")){
			Alert aceQuestion = new Alert(AlertType.CONFIRMATION, "Ace value is defaulted to 11. Do you want the ace to equal 1?", ButtonType.YES, ButtonType.NO);
			aceQuestion.showAndWait();
			if (aceQuestion.getResult() == ButtonType.YES) {
				userPoints = userPoints - 10;
			}
		}
		cardsLeft --;
		lblCardsLeftInDeck.setText("Cards Left: " + cardsLeft);
	}
	
	// method for randomly drawing a card from the remaining cards in the deck for the dealer's first two cards. 
	// this is different from the RandomCard method because it doesn't not ask the user to determine the value of the dealer's ace.
	public void RandomCardDealer(){
		cardIndex = 0 + (int) (Math.random() * ((cardNames.length-1) + 1));
		Validate();
		dealtCards.add(cardNames[cardIndex]);
		cardsLeft --;
		lblCardsLeftInDeck.setText("Cards Left: " + cardsLeft);
	}

	// method for randomly drawing a card from the remaining cards in the deck for the dealer after the user has decided to stay.
	public void RandomCardDealerHit(){
		cardIndex = 0 + (int) (Math.random() * ((cardNames.length-1) + 1));
		Validate();
		dealtCards.add(cardNames[cardIndex]);
		if (cardNames[cardIndex].equals("s1") || cardNames[cardIndex].equals("d1") || cardNames[cardIndex].equals("h1") || cardNames[cardIndex].equals("c1")){
			if((dealerPoints + 11) >21){
				dealerPoints = dealerPoints - 10;
			}
		}
		cardsLeft --;
		lblCardsLeftInDeck.setText("Cards Left: " + cardsLeft);
	}
	
	// method to validate the card that was randomly drawn to be it has not already been used. If the original card has been drawn, the card will be drawn until
	// an undealt card is selected.
	public void Validate(){
		do{
			valid = true;
			if (dealtCards.contains(cardNames[cardIndex])){
				valid = false;
				cardIndex = 0 + (int) (Math.random() * ((cardNames.length-1) + 1));
			}
		} while(!valid);
	}
	
	// method for resetting the first fours cards face down and clear all other cards. 
	public void ResetHands(){
		userPoints = 0;
		dealerPoints = 0;
		dealerCard1.setImage(new Image("/images/b1fv.png"));
		dealerCard2.setImage(new Image("/images/b1fv.png"));
		userCard1.setImage(new Image("/images/b1fv.png"));
		userCard2.setImage(new Image("/images/b1fv.png"));
		dealerCard3.setImage(null);
		dealerCard4.setImage(null);
		dealerCard5.setImage(null);
		dealerCard6.setImage(null);
		userCard3.setImage(null);
		userCard4.setImage(null);
		userCard5.setImage(null);
		userCard6.setImage(null);		
	}
	
	// method for clear the dealtCards arraylist and setting cardsLeft back to 52.
	public void Shuffle(){
		dealtCards.clear();
		ResetHands();
		cardsLeft = 52;
		lblCardsLeftInDeck.setText("Cards Left: " + cardsLeft);
		lblActionField.setText("Click Deal Cards to Start");
	}
	
	// method to check if the user has gone over 21 points.
	public void CheckPoints(){
		if(userPoints > 21){
			Loss();
		}
	}
	
	// method to check if the dealer has beat the user or lost to the user after the user clicks stay.
	public void CheckPointsDealer(){
		if(dealerPoints > 21){
			Win();
			needBreak = true;
		} else if(dealerPoints >= userPoints){
			Loss();
			needBreak = true;
		}
	}
	
	// method to increment the loss count and end the hand.
	public void Loss(){
		btHitMe.setDisable(true);
		btStay.setDisable(true);
		btShuffle.setDisable(false);
		btDeal.setDisable(false);
		userLosses ++;
		if(userPoints > 21){
			lblActionField.setText("User Busts. Dealer Wins!");	
		} else{
		lblActionField.setText("Dealer Wins!");
		}
		dealerCard1.setImage(new Image("/images/" + dealerFirstCard + ".png"));
		lblScore.setText("Wins / Losses: " + Integer.toString(userWins) + " / " + Integer.toString(userLosses));
	}
	
	// method to increment the win count and end the hand.
	public void Win(){
		btHitMe.setDisable(true);
		btStay.setDisable(true);
		btShuffle.setDisable(false);
		btDeal.setDisable(false);
		userWins ++;
		lblActionField.setText("User Wins!");
		lblScore.setText("Wins / Losses: " + Integer.toString(userWins) + " / " + Integer.toString(userLosses));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}