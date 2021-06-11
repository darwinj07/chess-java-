import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

//======================================================Don't modify below===============================================================//
enum PieceType {
	king, queen, bishop, knight, rook, pawn, none
}

enum PlayerColor {
	black, white, none
}

// Name: Yoonjeae Jung
// StudentID#: 2020-14094
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard() {
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}

	public final void initBoardStatus() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				chessBoardStatus[j][i] = new Piece();
		}
	}

	public final void initPieceImages() {
		pieceImage_b[0] = new ImageIcon(
				new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(
				new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(
				new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(
				new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(
				new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(
				new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

		pieceImage_w[0] = new ImageIcon(
				new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(
				new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(
				new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(
				new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(
				new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(
				new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}

	public ImageIcon getImageIcon(Piece piece) {
		if (piece.color.equals(PlayerColor.black)) {
			if (piece.type.equals(PieceType.king))
				return pieceImage_b[0];
			else if (piece.type.equals(PieceType.queen))
				return pieceImage_b[1];
			else if (piece.type.equals(PieceType.bishop))
				return pieceImage_b[2];
			else if (piece.type.equals(PieceType.knight))
				return pieceImage_b[3];
			else if (piece.type.equals(PieceType.rook))
				return pieceImage_b[4];
			else if (piece.type.equals(PieceType.pawn))
				return pieceImage_b[5];
			else
				return pieceImage_b[6];
		} else if (piece.color.equals(PlayerColor.white)) {
			if (piece.type.equals(PieceType.king))
				return pieceImage_w[0];
			else if (piece.type.equals(PieceType.queen))
				return pieceImage_w[1];
			else if (piece.type.equals(PieceType.bishop))
				return pieceImage_w[2];
			else if (piece.type.equals(PieceType.knight))
				return pieceImage_w[3];
			else if (piece.type.equals(PieceType.rook))
				return pieceImage_w[4];
			else if (piece.type.equals(PieceType.pawn))
				return pieceImage_w[5];
			else
				return pieceImage_w[6];
		} else
			return pieceImage_w[6];
	}

	public final void initializeGui() {
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		JButton startButton = new JButton("Reset");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initiateBoard();
			}
		});

		tools.add(startButton);
		tools.addSeparator();
		tools.add(message);

		chessBoard = new JPanel(new GridLayout(0, 8));
		chessBoard.setBorder(new LineBorder(Color.BLACK));
		gui.add(chessBoard);
		ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares[i].length; j++) {
				JButton b = new JButton();
				b.addActionListener(new ButtonListener(i, j));
				b.setMargin(buttonMargin);
				b.setIcon(defaultIcon);
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
					b.setBackground(Color.WHITE);
				else
					b.setBackground(Color.gray);
				b.setOpaque(true);
				b.setBorderPainted(false);
				chessBoardSquares[j][i] = b;
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				chessBoard.add(chessBoardSquares[j][i]);

		}
	}

	public final JComponent getGui() {
		return gui;
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				ChessBoard cb = new ChessBoard();
				JFrame f = new JFrame("Chess");
				f.add(cb.getGui());
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setLocationByPlatform(true);
				f.setResizable(false);
				f.pack();
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(r);
	}

	// ================================Utilize these
	// functions========================================//

	class Piece {
		PlayerColor color;
		PieceType type;

		Piece() {
			color = PlayerColor.none;
			type = PieceType.none;
		}

		Piece(PlayerColor color, PieceType type) {
			this.color = color;
			this.type = type;
		}
	}

	public void setIcon(int x, int y, Piece piece) {// sets the coordinate to piece
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}

	public Piece getIcon(int x, int y) {
		return chessBoardStatus[y][x];
	}

	public void markPosition(int x, int y) {
		chessBoardSquares[y][x].setBackground(Color.pink);
	}

	public void unmarkPosition(int x, int y) {
		if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0))
			chessBoardSquares[y][x].setBackground(Color.WHITE);
		else
			chessBoardSquares[y][x].setBackground(Color.gray);
	}

	public void setStatus(String inpt) {
		message.setText(inpt);
	}

	public void initiateBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for (int i = 0; i < 8; i++) {
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
	// ======================================================Don't modify
	// above==============================================================//

	// ======================================================Implement
	// below=================================================================//
	private boolean end;
	private PlayerColor turn, other, temp;
	private int[] selpiece = new int[2];

	class ButtonListener implements ActionListener {
		int x;
		int y;

		ButtonListener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void actionPerformed(ActionEvent e) {
			if (!end) {
				if (chessBoardSquares[this.y][this.x].getBackground().equals(Color.pink)) {
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++)
							unmarkPosition(i, j);
					}

					if (chessBoardStatus[this.y][this.x].type.equals(PieceType.king)) {
						end = true;
						if (turn.equals(PlayerColor.black))
							setStatus("BLACK WINS");
						else
							setStatus("WHITE WINS");
					}

					setIcon(this.x, this.y, new Piece(chessBoardStatus[selpiece[1]][selpiece[0]].color,
							chessBoardStatus[selpiece[1]][selpiece[0]].type));
					setIcon(selpiece[0], selpiece[1], new Piece());
					if (end) {
						if (turn.equals(PlayerColor.black))
							setStatus("BLACK WINS");
						else
							setStatus("WHITE WINS");
						return;
					}
					if (turn.equals(PlayerColor.black))
						setStatus("WHITE's TURN");
					else
						setStatus("BLACK's TURN");
					check();
					temp = turn;
					turn = other;
					other = temp;

				} else {
					// 말 선택한 경우
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++)
							unmarkPosition(i, j);
					}
					if (turn.equals(PlayerColor.black)) {
						if (chessBoardStatus[this.y][this.x].color.equals(PlayerColor.black)) {
							pyoshi(this.x, this.y);
							selpiece[0] = this.x;
							selpiece[1] = this.y;
						}
					} else if (chessBoardStatus[this.y][this.x].color.equals(PlayerColor.white)) {
						pyoshi(this.x, this.y);
						selpiece[0] = this.x;
						selpiece[1] = this.y;
					}
				}
			}
		}

		void check() {
			int kingx = -1;
			int kingy = -1;
			int checklist[][] = { { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 },
					{ -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 },
					{ -1, -1 } };
			int kinglist[][] = { { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 },
					{ -1, -1 } };
			int block[][]={ { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }};
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (chessBoardStatus[j][i].color.equals(turn)) {
						pyoshi(i, j);

						outer: // find other king
						for (int a = 0; a < 8; a++) {
							for (int b = 0; b < 8; b++) {
								if (chessBoardStatus[b][a].type.equals(PieceType.king)
										&& chessBoardStatus[b][a].color.equals(other)
										&& chessBoardSquares[b][a].getBackground().equals(Color.pink)) {
									if (turn.equals(PlayerColor.black))
										setStatus("WHITE's TURN/CHECK");
									else
										setStatus("BLACK's TURN/CHECK");
									for (int c = 0; c < 16; c++) {
										if (checklist[c][0] < 0) {
											checklist[c][0] = i;
											checklist[c][1] = j;
											break;
										}
									}

									break outer;
								}
							}
						}
						unpyoshi(i,j);

					}
				}
			}
			//if both are check, then it can't be checkmate. return.
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(chessBoardStatus[j][i].color.equals(other)) pyoshi(i,j);
				}
			}
			outer:
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(chessBoardStatus[j][i].type.equals(PieceType.king)&&chessBoardStatus[j][i].color.equals(turn)){
						if(chessBoardSquares[j][i].getBackground().equals(Color.pink)){
							for(int a=0;a<8;a++){
								for(int b=0;b<8;b++){
									unmarkPosition(a,b);
								}
							}
							return;
						}
						break outer;
					} 
				}
			}
			for(int a=0;a<8;a++){
				for(int b=0;b<8;b++){
					unmarkPosition(a,b);
				}
			}
			// checkmate
			checkmate: while (checklist[0][0] >= 0) {// when check
				// find king, change around king to white
				outer: for (int a = 0; a < 8; a++) {
					for (int b = 0; b < 8; b++) {
						if (chessBoardStatus[b][a].type.equals(PieceType.king)
								&& chessBoardStatus[b][a].color.equals(other)) {
							// found king
							kingx = a;
							kingy = b;
							// change color and save to kinglist
							for (int i = a - 1; i <= a + 1; i++) {
								for (int j = b - 1; j <= b + 1; j++) {
									if (i >= 0 && i < 8 && j >= 0 && j < 8
											&& chessBoardStatus[j][i].color.equals(turn)) {
										chessBoardStatus[j][i].color = other;
										// add tokinglist
										for (int c = 0; c < 8; c++) {
											if (kinglist[c][0] < 0) {
												kinglist[c][0] = i;
												kinglist[c][1] = j;
												break;
											}
										}
									}
								}
							}

							break outer;
						}
					}
				}
				//pyoshi for not around king
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (chessBoardStatus[j][i].color.equals(turn)
						&&!(i>=kingx-1&&i<=kingx+1&&j>=kingy-1&&j<=kingy+1)) {
							pyoshi(i, j);
						}
					}
				}
				// change color back around king
				for (int c = 0; c < 8; c++) {
					if (kinglist[c][0] < 0)
						break;
					chessBoardStatus[kinglist[c][1]][kinglist[c][0]].color = turn;
				}
				//pyoshi for around king
				for (int i = kingx - 1; i <= kingx + 1; i++) {
					for (int j = kingy - 1; j <= kingy + 1; j++) {
						if (i >= 0 && i < 8 && j >= 0 && j < 8 && chessBoardStatus[j][i].color.equals(turn)) {
							pyoshi(i,j);
						}
					}
				}

				//see if king can escape
				for (int i = kingx - 1; i <= kingx + 1; i++) {
					for (int j = kingy - 1; j <= kingy + 1; j++) {
						if (i >= 0 && i < 8 && j >= 0 && j < 8 && !chessBoardStatus[j][i].color.equals(other)
								&& !chessBoardSquares[j][i].getBackground().equals(Color.pink)) {
							break checkmate;
						}
					}
				}
				// king can't escape, unmark
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						unmarkPosition(i, j);
					}
				}
				//save the area that needs to be blocked
				switch (chessBoardStatus[checklist[0][1]][checklist[0][0]].type) {
					case pawn:
						block[0]=checklist[0];//might neeed change;
						break;
					case bishop:
						if (kingx + kingy == checklist[0][0] + checklist[0][1]) {
							int a = kingx + kingy;
							if (kingx > checklist[0][0]) {
								for (int i = checklist[0][0]; i < kingx; i++) {
									block[i-checklist[0][0]][0]=i;
									block[i-checklist[0][0]][1]=a-i;
								}
							} else {
								for (int i = checklist[0][0]; i > kingx; i--) {
									block[-i+checklist[0][0]][0]=i;
									block[-i+checklist[0][0]][1]=a-i;
								}
							}
						} else {
							int b = kingx - kingy;
							if (kingx > checklist[0][0]) {
								for (int i = checklist[0][0]; i < kingx; i++) {
									block[i-checklist[0][0]][0]=i;
									block[i-checklist[0][0]][1]=i-b;
								}
							} else {
								for (int i = checklist[0][0]; i > kingx; i--) {
									block[-i+checklist[0][0]][0]=i;
									block[-i+checklist[0][0]][1]=i-b;
								}
							}
						}
						break;
					//deleted king. if king checked the other king, that can never be checkmate.
					case king:
						break checkmate;
					case knight:
						block[0]=checklist[0];
						break;
					case queen:
						if (kingx + kingy == checklist[0][0] + checklist[0][1]) {
							int a = kingx + kingy;
							if (kingx > checklist[0][0]) {
								for (int i = checklist[0][0]; i < kingx; i++) {
									block[i-checklist[0][0]][0]=i;
									block[i-checklist[0][0]][1]=a-i;
								}
							} else {
								for (int i = checklist[0][0]; i > kingx; i--) {
									block[-i+checklist[0][0]][0]=i;
									block[-i+checklist[0][0]][1]=a-i;
								}
							}
						} else if(kingx - kingy == checklist[0][0] - checklist[0][1]){
							int b = kingx - kingy;
							if (kingx > checklist[0][0]) {
								for (int i = checklist[0][0]; i < kingx; i++) {
									block[i-checklist[0][0]][0]=i;
									block[i-checklist[0][0]][1]=i-b;
								}
							} else {
								for (int i = checklist[0][0]; i > kingx; i--) {
									block[-i+checklist[0][0]][0]=i;
									block[-i+checklist[0][0]][1]=i-b;
								}
							}
						}
						else if (kingx == checklist[0][0]) {
							if (kingy > checklist[0][1]) {
								// kingy~checklist[0][1]+1
								for (int i = checklist[0][1]; i < kingy; i++) {
									block[i-checklist[0][1]][0]=kingx;
									block[i-checklist[0][1]][1]=i;
								}
							} else {
								for (int i = checklist[0][1]; i > kingy; i--) {
									block[-i+checklist[0][1]][0]=kingx;
									block[-i+checklist[0][1]][1]=i;
								}
							}

						} else {
							if (kingx > checklist[0][0]) {
								// kingy~checklist[0][1]+1
								for (int i = checklist[0][0]; i < kingx; i++) {
									block[i-checklist[0][0]][0]=i;
									block[i-checklist[0][0]][1]=kingy;
								}
							} else {
								for (int i = checklist[0][0]; i > kingx; i--) {
									block[-i+checklist[0][0]][0]=i;
									block[-i+checklist[0][0]][1]=kingy;
								}
							}
						}
						break;
					case rook:
						if (kingx == checklist[0][0]) {
							if (kingy > checklist[0][1]) {
								// kingy~checklist[0][1]+1
								for (int i = checklist[0][1]; i < kingy; i++) {
									block[i-checklist[0][1]][0]=kingx;
									block[i-checklist[0][1]][1]=i;
								}
							} else {
								for (int i = checklist[0][1]; i > kingy; i--) {
									block[-i+checklist[0][1]][0]=kingx;
									block[-i+checklist[0][1]][1]=i;
								}
							}

						} else {
							if (kingx > checklist[0][0]) {
								// kingy~checklist[0][1]+1
								for (int i = checklist[0][0]; i < kingx; i++) {
									block[i-checklist[0][0]][0]=i;
									block[i-checklist[0][0]][1]=kingy;
								}
							} else {
								for (int i = checklist[0][0]; i > kingx; i--) {
									block[-i+checklist[0][0]][0]=i;
									block[-i+checklist[0][0]][1]=kingy;
								}
							}

						}
						break;
				}
				//pyoshi each white piece, see if block list it pink. if true then remove that white piece(color=none) and check if it is check.
				PlayerColor tempcolor;
				PieceType temptype;
				for(int i=0;i<8;i++){
					for (int j=0;j<8;j++){
						if(chessBoardStatus[j][i].color.equals(other)&&!chessBoardStatus[j][i].type.equals(PieceType.king)){
							pyoshi(i,j);
							for(int k=0;k<7;k++){
								if(block[k][0]<0) break;
								if(chessBoardSquares[block[k][1]][block[k][0]].getBackground().equals(Color.pink)){
									unpyoshi(i,j);
									chessBoardStatus[j][i].color=PlayerColor.none;
					
									tempcolor=chessBoardStatus[block[k][1]][block[k][0]].color;
									chessBoardStatus[block[k][1]][block[k][0]].color=other;
									temptype=chessBoardStatus[block[k][1]][block[k][0]].type;
									chessBoardStatus[block[k][1]][block[k][0]].type=chessBoardStatus[j][i].type;
									chessBoardStatus[j][i].type=PieceType.none;
									for(int a=0;a<8;a++){
										for(int b=0;b<8;b++){
											if(chessBoardStatus[b][a].color.equals(turn)) pyoshi(a,b);
										}
									}
									chessBoardStatus[j][i].color=other;
									chessBoardStatus[block[k][1]][block[k][0]].color=tempcolor;
									chessBoardStatus[j][i].type=chessBoardStatus[block[k][1]][block[k][0]].type;
									chessBoardStatus[block[k][1]][block[k][0]].type=temptype;
									if(!chessBoardSquares[kingy][kingx].getBackground().equals(Color.pink)) {

										break checkmate;
									}
									for(int a=0;a<8;a++){
										for(int b=0;b<8;b++){
											unmarkPosition(a, b);
										}
									}
				

								}
							}
							unpyoshi(i,j);
						}
						}
				}

				end = true;
				setStatus("CHECKMATE");
				break;
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					unmarkPosition(i, j);
				}
			}

		}

		private void pyoshi(int x, int y) {
			int a, b;
			switch (chessBoardStatus[y][x].type) {
				case king:
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (0 <= i && i < 8 && 0 <= j && j < 8 && !chessBoardStatus[j][i].color.equals(chessBoardStatus[y][x].color)) {
								markPosition(i, j);
							}
						}
					}
					unmarkPosition(x, y);
					break;
				case bishop:
					a = x + y;
					b = x - y;
					if (a <= 7) {
						for (int i = x + 1; i <= a; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= a - 7; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
					}
					if (b >= 0) {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= b; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7 + b; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
					}
					break;
				case knight:
					int k[][] = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
							{ -1, -2 } };
					for (int i = 0; i < 8; i++) {
						if (0 <= x + k[i][0] && x + k[i][0] < 8 && y + k[i][1] >= 0 && y + k[i][1] < 8) {
							if (!chessBoardStatus[y + k[i][1]][x + k[i][0]].color.equals(chessBoardStatus[y][x].color))
								markPosition(x + k[i][0], y + k[i][1]);
						}
					}
					break;
				case pawn:
					if (chessBoardStatus[y][x].color.equals(PlayerColor.white)) {
						if (0 <= x - 1 && 0 <= y - 1) {
							if (chessBoardStatus[y - 1][x - 1].color.equals(PlayerColor.black))
								markPosition(x - 1, y - 1);
						}
						if (8 > y + 1 && 0 <= x - 1) {
							if (chessBoardStatus[y + 1][x - 1].color.equals(PlayerColor.black))
								markPosition(x - 1, y + 1);
						}
						if (x == 6) {
							for (int i = 5; i >= 4; i--) {
								if (chessBoardStatus[y][i].type.equals(PieceType.none))
									markPosition(i, y);
								else
									break;
							}
						} else {
							if (x - 1 >= 0) {
								if (chessBoardStatus[y][x - 1].type.equals(PieceType.none))
									markPosition(x - 1, y);
							}
						}
					} else {
						if (8 > x + 1 && 8 > y + 1) {
							if (chessBoardStatus[y + 1][x + 1].color.equals(PlayerColor.white))
								markPosition(x + 1, y + 1);
						}
						if (8 > x + 1 && 0 <= y - 1) {
							if (chessBoardStatus[y - 1][x + 1].color.equals(PlayerColor.white))
								markPosition(x + 1, y - 1);
						}
						if (x == 1) {
							for (int i = 2; i <= 3; i++) {
								if (chessBoardStatus[y][i].type.equals(PieceType.none))
									markPosition(i, y);
								else
									break;
							}
						} else {
							if (x + 1 < 8) {
								if (chessBoardStatus[y][x + 1].type.equals(PieceType.none))
									markPosition(x + 1, y);
							}
						}
					}
					break;
				case queen:
					for (int i = x + 1; i < 8; i++) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							markPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
								markPosition(i, y);
							break;
						}
					}
					for (int i = x - 1; i >= 0; i--) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							markPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
								markPosition(i, y);
							break;
						}
					}
					for (int j = y + 1; j < 8; j++) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							markPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
								markPosition(x, j);
							break;
						}
					}
					for (int j = y - 1; j >= 0; j--) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							markPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
								markPosition(x, j);
							break;
						}
					}
					a = x + y;
					b = x - y;
					if (a <= 7) {
						for (int i = x + 1; i <= a; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= a - 7; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								markPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, a - i);
								break;
							}
						}
					}
					if (b >= 0) {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= b; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7 + b; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								markPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
									markPosition(i, i - b);
								break;
							}
						}
					}
					break;
				case rook:
					for (int i = x + 1; i < 8; i++) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							markPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
								markPosition(i, y);
							break;
						}
					}
					for (int i = x - 1; i >= 0; i--) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							markPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
								markPosition(i, y);
							break;
						}
					}
					for (int j = y + 1; j < 8; j++) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							markPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
								markPosition(x, j);
							break;
						}
					}
					for (int j = y - 1; j >= 0; j--) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							markPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
								markPosition(x, j);
							break;
						}
					}
					break;
			}

		}
		private void unpyoshi(int x, int y) {
			int a, b;
			switch (chessBoardStatus[y][x].type) {
				case king:
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (0 <= i && i < 8 && 0 <= j && j < 8 && !chessBoardStatus[j][i].color.equals(chessBoardStatus[y][x].color)) {
								unmarkPosition(i, j);
							}
						}
					}
					break;
				case bishop:
					a = x + y;
					b = x - y;
					if (a <= 7) {
						for (int i = x + 1; i <= a; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
									unmarkPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= a - 7; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
					}
					if (b >= 0) {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= b; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7 + b; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
					}
					break;
				case knight:
					int k[][] = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
							{ -1, -2 } };
					for (int i = 0; i < 8; i++) {
						if (0 <= x + k[i][0] && x + k[i][0] < 8 && y + k[i][1] >= 0 && y + k[i][1] < 8) {
							if (!chessBoardStatus[y + k[i][1]][x + k[i][0]].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(x + k[i][0], y + k[i][1]);
						}
					}
					break;
				case pawn:
					if (chessBoardStatus[y][x].color.equals(PlayerColor.white)) {
						if (0 <= x - 1 && 0 <= y - 1) {
							if (chessBoardStatus[y - 1][x - 1].color.equals(PlayerColor.black))
							unmarkPosition(x - 1, y - 1);
						}
						if (8 > y + 1 && 0 <= x - 1) {
							if (chessBoardStatus[y + 1][x - 1].color.equals(PlayerColor.black))
							unmarkPosition(x - 1, y + 1);
						}
						if (x == 6) {
							for (int i = 5; i >= 4; i--) {
								if (chessBoardStatus[y][i].type.equals(PieceType.none))
								unmarkPosition(i, y);
								else
									break;
							}
						} else {
							if (x - 1 >= 0) {
								if (chessBoardStatus[y][x - 1].type.equals(PieceType.none))
								unmarkPosition(x - 1, y);
							}
						}
					} else {
						if (8 > x + 1 && 8 > y + 1) {
							if (chessBoardStatus[y + 1][x + 1].color.equals(PlayerColor.white))
							unmarkPosition(x + 1, y + 1);
						}
						if (8 > x + 1 && 0 <= y - 1) {
							if (chessBoardStatus[y - 1][x + 1].color.equals(PlayerColor.white))
							unmarkPosition(x + 1, y - 1);
						}
						if (x == 1) {
							for (int i = 2; i <= 3; i++) {
								if (chessBoardStatus[y][i].type.equals(PieceType.none))
								unmarkPosition(i, y);
								else
									break;
							}
						} else {
							if (x + 1 < 8) {
								if (chessBoardStatus[y][x + 1].type.equals(PieceType.none))
								unmarkPosition(x + 1, y);
							}
						}
					}
					break;
				case queen:
					for (int i = x + 1; i < 8; i++) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							unmarkPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(i, y);
							break;
						}
					}
					for (int i = x - 1; i >= 0; i--) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							unmarkPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(i, y);
							break;
						}
					}
					for (int j = y + 1; j < 8; j++) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							unmarkPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(x, j);
							break;
						}
					}
					for (int j = y - 1; j >= 0; j--) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							unmarkPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(x, j);
							break;
						}
					}
					a = x + y;
					b = x - y;
					if (a <= 7) {
						for (int i = x + 1; i <= a; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
						for (int i = x - 1; i >= a - 7; i--) {
							if (chessBoardStatus[a - i][i].type.equals(PieceType.none)) {
								unmarkPosition(i, a - i);
							} else {
								if (!chessBoardStatus[a - i][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, a - i);
								break;
							}
						}
					}
					if (b >= 0) {
						for (int i = x + 1; i <= 7; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= b; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
					} else {
						for (int i = x + 1; i <= 7 + b; i++) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
						for (int i = x - 1; i >= 0; i--) {
							if (chessBoardStatus[i - b][i].type.equals(PieceType.none)) {
								unmarkPosition(i, i - b);
							} else {
								if (!chessBoardStatus[i - b][i].color.equals(chessBoardStatus[y][x].color))
								unmarkPosition(i, i - b);
								break;
							}
						}
					}
					break;
				case rook:
					for (int i = x + 1; i < 8; i++) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							unmarkPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(i, y);
							break;
						}
					}
					for (int i = x - 1; i >= 0; i--) {
						if (chessBoardStatus[y][i].type.equals(PieceType.none)) {
							unmarkPosition(i, y);
						} else {
							if (!chessBoardStatus[y][i].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(i, y);
							break;
						}
					}
					for (int j = y + 1; j < 8; j++) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							unmarkPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(x, j);
							break;
						}
					}
					for (int j = y - 1; j >= 0; j--) {
						if (chessBoardStatus[j][x].type.equals(PieceType.none)) {
							unmarkPosition(x, j);
						} else {
							if (!chessBoardStatus[j][x].color.equals(chessBoardStatus[y][x].color))
							unmarkPosition(x, j);
							break;
						}
					}
					break;
			}

		}
	}

	void onInitiateBoard() {
		turn = PlayerColor.black;
		other = PlayerColor.white;
		end = false;
		setStatus("BLACK's TURN");

	}
}