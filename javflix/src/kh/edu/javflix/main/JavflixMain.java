package kh.edu.javflix.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kh.edu.javflix.cart.Cart;
import kh.edu.javflix.ott.Ott;
import kh.edu.javflix.user.User;

public class JavflixMain {
	public static Scanner sc = new Scanner(System.in);
	public static String newName = null;
	public static int newPassword = 0;
	static Cart cart = new Cart();
	public static int ottNumber = 0;
	public static int cartCount = 0;
	public static int downCount = 0;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welcome to Javflix!");
		boolean flag = false;
		ArrayList<User> userList = new ArrayList<>();
		List<User> userStream = null;
		ArrayList<Ott> ottList = new ArrayList<>();
		ArrayList<Ott> cartList = new ArrayList<Ott>();
		User user = new User();
		while (!flag) {
			System.out.println("메뉴를 선택하세요.");
			System.out.print("1. 사용자 로그인, 2. 관리자 로그인  ");
			int login = sc.nextInt();
			loadFile(userList);
			printUser(userList);
			loadOtt(ottList);
			ottList.stream().forEach(s -> s.setViewer());
			switch (login) {
			case 1:
				userStream = userLogin(userList);
				flag = false;
				boolean menuFlag = false;
				while (!menuFlag) {
					int menu = loginMenu(userStream);
					switch (menu) {
					case 1:
						int number = profileChange(userStream);
						if (number == 2) {
							boolean menuFlag2 = false;
							while (!menuFlag2) {
								menu = loginMenu2(userStream);
								switch (menu) {
								case 1:
									int number2 = profileChange(userStream);
									if (number2 == 1)
										menuFlag2 = true;
									break;
								case 2:
									accountManage(userList, userStream);
									break;
								case 3:
									search(ottList);
									break;
								case 4:
									top5(ottList);
									break;
								case 5:
									categorySearch(ottList);
									break;
								case 6:
									countrySearch(ottList);
									break;
								case 7:
									cartList = loadCart();
									break;
								case 8:
									cartList = loadCart();
									searchDownLoad(cartList);
									break;
								case 9:
									cartList = loadCart();
									searchSeen(cartList);
									break;
								case 10:
									customerService(userStream);
									break;
								case 11:
									menuFlag = true;
									break;
								default:
									System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
									break;
								}
							}
						}
						break;
					case 2:
						accountManage(userList, userStream);
						break;
					case 3:
						search(ottList);
						break;
					case 4:
						top5(ottList);
						break;
					case 5:
						categorySearch(ottList);
						break;
					case 6:
						countrySearch(ottList);
						break;
					case 7:
						cartList = loadCart();
						cartList = addList(cartList);
						break;
					case 8:
						cartList = loadCart();
						cartList = searchDownLoad(cartList);
						break;
					case 9:
						cartList = loadCart();
						cartList = searchSeen(cartList);
						break;
					case 10:
						customerService(userStream);
						break;
					case 11:
						flag = true;
						break;
					default:
						System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
						break;
					}
				}
				break;
			case 2:
				adminLogin(user);
				adminMenu(userStream, ottList);

				break;
			case 3:
				flag = true;
				break;
			default:
				break;
			}
		}
	}

	private static void adminMenu(List<User> userStream, ArrayList<Ott> ottList) {
		System.out.println("메뉴를 선택하세요.");
		System.out.println("1. OTT 추가  2. OTT 제거");
		System.out.println("3. OTT 조회수 조회 4. 유저 관리");
		System.out.print("5. 고객센터   6. 로그아웃   ");
		ObjectOutputStream oos = null;
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			List<Integer> ottAdd = ottList.stream().map(s -> s.getNumber()).collect(Collectors.toList());
			sc.nextLine();
			System.out.print("새 OTT 제목를 입력하세요 : ");
			String newTitle = sc.nextLine();
			System.out.print("새 OTT 국가를 입력하세요 : ");
			String newCountry = sc.nextLine();
			System.out.print("새 OTT 카테고리를 입력하세요 : ");
			String newGenre = sc.nextLine();
			System.out.print("새 OTT 스토리를 입력하세요 : ");
			String newStory = sc.nextLine();
			System.out.print("새 OTT 배우를 입력하세요 : ");
			String newActor = sc.nextLine();
			System.out.print("새 OTT 감독을 입력하세요 : ");
			String newDirector = sc.nextLine();
			System.out.print("새 OTT 평점을 입력하세요 : ");
			String newRate = sc.nextLine();
			System.out.print("새 OTT 연령제한을 입력하세요 : ");
			String newAge = sc.nextLine();
			Ott ott = new Ott((ottAdd.getLast() + 1), newTitle, newCountry, newGenre, newStory, newActor, newDirector,
					"2024", newRate, newAge, "미시청", "미다운로드", 1);
			ottList.add(ott);
			try {
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Ott.txt"), true)));
				oos.writeObject(ottList);
				System.out.println("새 OTT <" + newTitle + "> 추가 되었습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			cart.printOttStream(ottList);
			System.out.print("제거할 OTT 번호를 입력하세요 : ");
			int number = sc.nextInt();
			List<Ott> ottStream = ottList.stream().filter(s -> s.getNumber() != number).collect(Collectors.toList());
			try {
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Ott.txt"))));
				oos.writeObject(ottStream);
				System.out.println("목록을 삭제했습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ottStream.stream().filter(s -> s.getNumber() > number).forEach(s -> s.setNumber(s.getNumber() - 1));
			try {
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Ott.txt"))));
				oos.writeObject(ottStream);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case 3:
			List<Ott> str = viewCheck(ottList);
			cart.printViewer(str);
			break;
		case 4:
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1. 유저 추가 2. 유저 제거");
			System.out.print("3. 유저 조회 4. 뒤로가기   ");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.print("신규 사용자 이름을 입력하세요 : ");
				String newName = sc.nextLine();
				System.out.print("신규 사용자 ID를 입력하세요 : ");
				String newId = sc.nextLine();
				System.out.print("신규 사용자 비밀번호를 입력하세요 : ");
				int newPassword = sc.nextInt();
				System.out.print("신규 사용자 전화번호를 입력하세요 : ");
				String newPhone = sc.nextLine();
				System.out.print("신규 사용자 프로필 이름을 입력하세요 : ");
				String newProfileName = sc.nextLine();
				System.out.print("신규 사용자 프로필 비밀번호를 입력하세요 : ");
				int newProfilePassword = sc.nextInt();
				System.out.print("신규 사용자 멤버쉽 등급을 입력하세요 : ");
				String newMembership = sc.nextLine();
				User user = new User(newName, newId, newPassword, newPhone, newProfileName, newProfilePassword, null, 0,
						"silver");
				userStream.add(user);
				try {
					oos = new ObjectOutputStream(
							new BufferedOutputStream(new FileOutputStream(new File("User.txt"), true)));
					oos.writeObject(userStream);
					System.out.println("신규 사용자 < " + newName + "> 추가 되었습니다.");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				break;
			}
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			break;
		}
	}

	private static List<Ott> viewCheck(ArrayList<Ott> ottList) {
		List<Ott> topStream = ottList.stream().sorted(Comparator.comparing(Ott::getViewer).reversed())
				.collect(Collectors.toList());
		return topStream;
	}

	private static void top5(ArrayList<Ott> ottList) throws InterruptedException {
		List<Ott> topStream = ottList.stream().sorted(Comparator.comparing(Ott::getViewer).reversed())
				.collect(Collectors.toList());
		List<Ott> stream = topStream.stream().limit(5).collect(Collectors.toList());
		cart.printOttStream(stream);

		select(stream);
	}

	private static void adminLogin(User user) {
		for (;;) {
			System.out.print("관리자 ID를 입력하세요 : ");
			String adminId = sc.nextLine();
			System.out.println("관리자 비밀번호를 입력하세요 : ");
			String adminPassword = sc.nextLine();
			if (adminId.equals(user.getAdminid()) && adminPassword.equals(adminPassword)) {
				System.out.println("로그인 성공!");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
			}
		}
	}

	private static void customerService(List<User> userStream) {
		for (;;) {
			System.out.println("Javflix 고객센터 전화번호 : 010-9054-1146 | 이메일 주소 : javparadise@gmail.com");
			System.out.print("Q&A 남기기 Y | N : ");
			String qA = sc.nextLine().toLowerCase();
			if (qA.equals("y")) {
				System.out.print("질문사항을 입력하세요 : (완료시 엔터)");
				String qAString = sc.nextLine();
				System.out.println(userStream.stream().map(s -> s.getName()).collect(Collectors.toList()) + "님의 질문사항 "
						+ qAString + "이 등록되었습니다. 빠른 시일 내로 답변드리겠습니다. ");
			} else if (qA.equals("n")) {
				break;
			} else {
				System.out.println("잘못 입력했습니다. ");
			}
		}
	}

	private static void countrySearch(ArrayList<Ott> ottList) throws InterruptedException {
		List<Ott> ottStream = null;
		boolean flag = false;
		while (!flag) {
			System.out.println("나라를 선택하세요. (뒤로가기 -1)");
			System.out.print("1, 한국 2. 미국, 3. 일본, 4. 유럽, 5. 스페인  ");
			int number = sc.nextInt();
			switch (number) {
			case 1:
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("한국")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 2:
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("미국")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 3:
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("일본")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 4:
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("유럽")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 5:
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("스페인")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case -1:
				flag = true;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
				break;
			}
		}
	}

	private static void categorySearch(ArrayList<Ott> ottList) throws InterruptedException {
		List<Ott> ottStream = null;
		boolean flag = false;
		while (!flag) {
			System.out.println("카테고리를 선택하세요. (뒤로가기 -1)");
			System.out.print("1, 로맨스 2. 드라마, 3. 액션, 4. 애니메이션, 5. 범죄, 6. 스릴러, 7. 코미디  ");
			int number = sc.nextInt();
			switch (number) {
			case 1:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("로맨스")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 2:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("드라마")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 3:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("액션")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 4:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("애니메이션")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 5:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("범죄")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 6:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("스릴러")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case 7:
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("코미디")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream);
				break;
			case -1:
				flag = true;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
				break;
			}
		}
	}

	private static void select(List<Ott> ottStream) throws InterruptedException {
		ObjectOutputStream oos = null;
		int numSelect = 0;
		System.out.print("OTT를 선택하세요 : ");
		ottNumber = sc.nextInt();
		List<Ott> ottList = ottStream.stream().filter(s -> s.getNumber() == ottNumber).collect(Collectors.toList());
		cart.printOttStream(ottList);
		System.out.print("1. 시청, 2. 찜하기  3. 뒤로가기  ");
		numSelect = sc.nextInt();
		for (;;) {
			if (numSelect == 1) {
				watch(ottList);
				break;
			} else if (numSelect == 2) {
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
					oos.writeObject(ottList);
				} catch (Exception e) {
					System.out.println("오류가발생" + e.toString());
				} finally {
					try {
						oos.close();
					} catch (IOException e) {
						System.out.println("oos.close() 에러발생" + e.toString());
					}
				}
				cartCount++;
				sc.nextLine();
				System.out.print("찜 목록에 추가되었습니다. 다운로드하시겠습니까? (Y | N)  ");
				String yN = sc.nextLine().toLowerCase();
				for (;;) {
					if (yN.equals("y")) {
						try {
							ottList.stream().forEach(s -> s.setIsDown("다운로드 완료"));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(ottList);
							System.out.print("다운로드 진행 중...");
							for (int i = 0; i < 10; i++) {
								System.out.print(i * 10 + "%..");
								Thread.sleep(200);
							}
							System.out.println("다운로드 완료!");
							downCount++;
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								oos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						break;
					} else if (yN.equals("n")) {
						break;
					} else {
						System.out.println("잘못 입력했습니다. 다시 입력하세요.");
					}
				}
				break;
			} else if (numSelect == 3) {
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}

	private static void watch(List<Ott> ottStream) throws InterruptedException {
		ObjectOutputStream oos = null;
		String[] story = null;
		if (cartCount == 1) {
			story = ottStream.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
			for (String data : story) {
				System.out.print(data.toString());
				Thread.sleep(100);
			}
			System.out.println("시청 완료!");
			try {
				ottStream.stream().forEach(s -> s.setIsSeen("시청 완료"));
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
				oos.writeObject(ottStream);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			story = ottStream.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
			for (String data : story) {
				System.out.print(data.toString());
				Thread.sleep(100);
			}
			System.out.println("시청 완료!");
			try {
				ottStream.stream().forEach(s -> s.setIsSeen("시청 완료"));
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
				oos.writeObject(ottStream);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static ArrayList<Ott> addList(ArrayList<Ott> cartList) throws InterruptedException {
		if (cartList != null) {
			List<Ott> ottStream = cartList;
			ObjectOutputStream oos = null;
			boolean flag = false;
			while (!flag) {
				cart.printOttStream(ottStream);
				System.out.println("메뉴를 선택하세요.");
				System.out.print("1. 시청 2. 목록 삭제 3. 다운로드 4. 뒤로가기  ");
				int number = sc.nextInt();
				switch (number) {
				case 1:
					watch(ottStream);
					break;
				case 2:
					ottStream = delete(cartList);
					if (ottStream == null) {
						System.out.println("찜 목록이 비었습니다. ");
						flag = true;
					}
					break;
				case 3:
					downLoad(cartList);
					break;
				case 4:
					flag = true;
					break;
				default:
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
					break;
				}
			}
		} else {
			System.out.println("찜 목록이 비었습니다. ");
		}
		return cartList;
	}

	private static void downLoad(ArrayList<Ott> cartList) throws InterruptedException {
		ObjectOutputStream oos = null;
		if (downCount == 1 && (cartList.stream().filter(s -> s.getIsDown().equals("다운로드 완료"))
				.collect(Collectors.toList())) != null) {
			System.out.println("이미 다운 받은 OTT입니다.");
		} else if (downCount == 1) {
			try {
				cartList.stream().forEach(s -> s.setIsDown("다운로드 완료"));
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
				oos.writeObject(cartList);
				System.out.print("다운로드 진행 중...");
				for (int i = 0; i < 10; i++) {
					System.out.print(i * 10 + "%..");
					Thread.sleep(200);
				}
				System.out.println("다운로드 완료!");
				downCount++;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.print("다운 받을 OTT를 선택하세요 : ");
			ottNumber = sc.nextInt();
			List<Ott> downStream = cartList.stream().filter(s -> s.getNumber() == ottNumber)
					.filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
			if (downStream != null) {
				System.out.println("이미 다운 받은 OTT입니다.");
			} else {
				cartList.stream().forEach(s -> s.setIsDown("다운로드 완료"));
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
					oos.writeObject(downStream);
					System.out.print("다운로드 진행 중...");
					for (int i = 0; i < 10; i++) {
						System.out.print(i * 10 + "%..");
						Thread.sleep(200);
					}
					System.out.println("다운로드 완료!");
					downCount++;
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static List<Ott> delete(List<Ott> cartList) {
		ObjectOutputStream oos = null;
		List<Ott> ottStream = null;
		if (cartCount == 1) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("Cart.txt");
				writer.print("");
				writer.close();
				System.out.println("목록을 삭제했습니다.");
				cartCount--;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("목록에서 제거할 OTT 번호를 선택하세요 : ");
			ottNumber = sc.nextInt();
			ottStream = cartList.stream().filter(s -> s.getNumber() != ottNumber).collect(Collectors.toList());
			try {
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
				oos.writeObject(ottStream);
				System.out.println("목록을 삭제했습니다.");
				cartCount--;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ottStream;
	}

	private static ArrayList<Ott> searchSeen(ArrayList<Ott> cartList) throws InterruptedException {
		if (cartList != null) {
			List<Ott> ottStream = cartList;
			ObjectOutputStream oos = null;
			boolean flag = false;
			while (!flag) {
				cart.printOttStream(ottStream);
				List<Ott> str = cartList.stream().filter(s -> s.getIsSeen().equals("시청 완료"))
						.collect(Collectors.toList());
				cart.printOttStream(str);
				System.out.println("메뉴를 선택하세요.");
				System.out.print("1. 시청 2. 목록 삭제 3. 찜하기 4. 다운로드 5. 뒤로가기   ");
				int number = sc.nextInt();
				switch (number) {
				case 1:
					watch(ottStream);
					break;
				case 2:
					ottStream = delete(cartList);
					if (ottStream == null) {
						System.out.println("찜 목록이 비었습니다. ");
						flag = true;
					}
					break;
				case 4:
					downLoad(cartList);
					break;
				case 3:
					System.out.print("OTT 번호를 선택하세요 : ");
					ottNumber = sc.nextInt();
					List<Ott> addStream = cartList.stream().filter(s -> s.getNumber() == ottNumber)
							.collect(Collectors.toList());
					try {
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(addStream);
					} catch (Exception e) {
						System.out.println("오류가발생" + e.toString());
					} finally {
						try {
							oos.close();
						} catch (IOException e) {
							System.out.println("oos.close() 에러발생" + e.toString());
						}
					}
					cartCount++;
					sc.nextLine();
					System.out.print("찜 목록에 추가되었습니다. 다운로드하시겠습니까? (Y | N)  ");
					String yN = sc.nextLine().toLowerCase();
					for (;;) {
						if (yN.equals("y")) {
							try {
								ottStream.stream().forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(ottStream);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								System.out.println("다운로드 완료!");
								downCount++;
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									oos.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							break;
						} else if (yN.equals("n")) {
							break;
						} else {
							System.out.println("잘못 입력했습니다. 다시 입력하세요.");
						}
					}
					break;
				case 5:
					flag = true;
					break;
				default:
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
					break;

				}
			}
		} else {
			System.out.println("찜 목록이 비었습니다. ");
		}
		return cartList;

	}

	private static ArrayList<Ott> searchDownLoad(ArrayList<Ott> cartList) throws InterruptedException {
		if (downCount != 0) {
			boolean flag = false;
			List<Ott> str = cartList.stream().filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
			cart.printOttStream(str);
			List<Ott> ottStream = null;
			ObjectOutputStream oos = null;
			String[] story = null;
			while (!flag) {
				System.out.println("메뉴를 선택하세요.");
				System.out.print("1. 시청, 2. 목록 삭제 3. 뒤로가기  ");
				int number = sc.nextInt();
				switch (number) {
				case 1:
					watch(str);
					break;
				case 2:
					if (downCount == 0) {
						System.out.println("다운 목록이 비었습니다. ");
					} else if (downCount == 1) {
						str.stream().forEach(s -> s.setIsSeen("미시청"));

					} else {
						System.out.print("제거할 OTT 번호를 선택하세요 : ");
						ottNumber = sc.nextInt();
						cartList.stream().filter(s -> s.getNumber() == ottNumber).forEach(s -> s.setIsSeen("미시청"));

					}
					break;
				case 3:
					flag = true;
					break;
				default:
					break;
				}
			}
		} else {
			System.out.println("다운 목록이 비었습니다. ");
		}
		return cartList;
	}

	private static ArrayList<Ott> loadCart() {
		ArrayList<Ott> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Cart.txt"))));
			list = (ArrayList<Ott>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private static void loadOtt(ArrayList<Ott> ottList) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Ott.txt"));
			String ottName = null;
			String[] readOtt = new String[13];
			while ((ottName = br.readLine()) != null) {
				readOtt[0] = ottName;
				readOtt[1] = br.readLine();
				readOtt[2] = br.readLine();
				readOtt[3] = br.readLine();
				readOtt[4] = br.readLine();
				readOtt[5] = br.readLine();
				readOtt[6] = br.readLine();
				readOtt[7] = br.readLine();
				readOtt[8] = br.readLine();
				readOtt[9] = br.readLine();
				readOtt[10] = br.readLine();
				readOtt[11] = br.readLine();
				readOtt[12] = br.readLine();
				Ott ott = new Ott(Integer.parseInt(readOtt[0]), readOtt[1], readOtt[2], readOtt[3], readOtt[4],
						readOtt[5], readOtt[6], readOtt[7], readOtt[8], readOtt[9], readOtt[10], readOtt[11],
						Integer.parseInt(readOtt[12]));
				ottList.add(ott);
			}
			br.close();
		} catch (NumberFormatException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void search(ArrayList<Ott> ottList) throws InterruptedException {
		cart.printOttList(ottList);
		List<Ott> ottStream = null;
		int numSelect = 0;
		ObjectOutputStream oos = null;
		BufferedWriter bw = null;
		String[] story = null;
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. OTT 선택 2. 검색  ");
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			System.out.print("OTT 번호를 선택하세요 : ");
			ottNumber = sc.nextInt();
			ottStream = ottList.stream().filter(s -> s.getNumber() == ottNumber).collect(Collectors.toList());
			cart.printOttStream(ottStream);
			System.out.print("1. 시청 2. 찜하기  ");
			numSelect = sc.nextInt();
			for (;;) {
				if (numSelect == 1) {
					story = ottStream.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
					for (String data : story) {
						System.out.print(data.toString());
						Thread.sleep(100);
					}
					System.out.println("시청 완료!");
					try {
						ottStream.stream().forEach(s -> s.setIsSeen("시청 완료"));
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(ottStream);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;
				} else if (numSelect == 2) {
					try {
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(ottStream);
					} catch (Exception e) {
						System.out.println("오류가발생" + e.toString());
					} finally {
						try {
							oos.close();
						} catch (IOException e) {
							System.out.println("oos.close() 에러발생" + e.toString());
						}
					}
					cartCount++;
					sc.nextLine();
					System.out.print("찜 목록에 추가되었습니다. 다운로드하시겠습니까? (Y | N)  ");
					String yN = sc.nextLine().toLowerCase();
					for (;;) {
						if (yN.equals("y")) {
							try {
								ottStream.stream().forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(ottStream);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								System.out.println("다운로드 완료!");
								downCount++;
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									oos.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							break;
						} else if (yN.equals("n")) {
							break;
						} else {
							System.out.println("잘못 입력했습니다. 다시 입력하세요.");
						}
					}
					break;
				} else {
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
			}

			break;
		case 2:
			sc.nextLine();
			System.out.print("검색할 키워드를 입력하세요 : ");
			String keyWord = sc.nextLine();
			ottStream = ottList.stream().filter(s -> s.getCountry().equals(keyWord) || s.getTitle().equals(keyWord)
					|| s.getStory().equals(keyWord) || s.getActor().equals(keyWord) || s.getDirector().equals(keyWord)
					|| s.getYear().equals(keyWord) || s.getAge().equals(keyWord)).collect(Collectors.toList());
			cart.printOttStream(ottStream);
			System.out.print("OTT 번호를 선택하세요 : ");
			ottNumber = sc.nextInt();
			List<Ott> str = ottList.stream().filter(s -> s.getNumber() == ottNumber).collect(Collectors.toList());
			cart.printOttStream(str);
			System.out.print("1. 시청, 2. 찜하기  ");
			numSelect = sc.nextInt();
			for (;;) {
				if (numSelect == 1) {
					story = str.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
					for (String data : story) {
						System.out.print(data.toString());
						Thread.sleep(100);
					}
					System.out.println("시청 완료!");
					try {
						ottStream.stream().forEach(s -> s.setIsSeen("시청 완료"));
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(str);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;
				} else if (numSelect == 2) {
					try {
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(str);
					} catch (Exception e) {
						System.out.println("오류가발생" + e.toString());
					} finally {
						try {
							oos.close();
						} catch (IOException e) {
							System.out.println("oos.close() 에러발생" + e.toString());
						}
					}
					cartCount++;
					sc.nextLine();
					System.out.print("찜 목록에 추가되었습니다. 다운로드하시겠습니까? (Y | N)  ");
					String yN = sc.nextLine().toLowerCase();
					for (;;) {
						if (yN.equals("y")) {
							try {
								str.stream().forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(ottStream);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								System.out.println("다운로드 완료!");
								downCount++;
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									oos.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							break;
						} else if (yN.equals("n")) {
							break;
						} else {
							System.out.println("잘못 입력했습니다. 다시 입력하세요.");
						}
					}
					break;
				} else {
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
				break;
			}
		default:
			System.out.println("잘못 입력했습니다. ");
			break;
		}

	}

	private static void accountManage(ArrayList<User> userList, List<User> userStream) {
		User user = new User();
		System.out.print("계정 비밀번호를 입력하세요 : ");
		int userPassword = sc.nextInt();
		List<User> stream = userStream.stream().filter(s -> s.getPassword() == userPassword)
				.collect(Collectors.toList());
		if (stream != null) {
			System.out.println("본인 인증 성공!");
			System.out.println("변경할 정보를 선택하세요.");
			System.out.print("1. 프로필 이름 변경, 2. 프로필 비밀번호 변경  ");
			int number = sc.nextInt();
			ObjectOutputStream oos = null;
			switch (number) {
			case 1:
				System.out.println("프로필을 선택하세요.");
				System.out.print("1. " + userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList())
						+ ", 2. " + userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()));
				int name = sc.nextInt();
				sc.nextLine();
				for (;;) {
					if (name == 1) {
						System.out.print("새로운 이름을 입력하세요 : ");
						newName = sc.nextLine();
						List<String> str = userStream.stream().map(s -> s.getProfileName1())
								.collect(Collectors.toList());
						String oN = str.getFirst();
						userList.stream().filter(s -> s.getProfileName1().equals(oN))
								.forEach(s -> s.setProfileName1(newName));
						break;
					} else if (name == 2) {
						userList.stream().map(s -> s.getProfileName2()).collect(Collectors.toList());
						System.out.print("새로운 이름을 입력하세요 : ");
						newName = sc.nextLine();
						List<String> str = userStream.stream().map(s -> s.getProfileName2())
								.collect(Collectors.toList());
						String oN = str.getFirst();
						userList.stream().filter(s -> s.getProfileName1().equals(oN))
								.forEach(s -> s.setProfileName1(newName));
						break;
					} else {
						System.out.println("잘못 입력했습니다. 다시 입력하세요.");
					}
				}
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("User.txt"))));
					oos.writeObject(userList);
					System.out.println("변경 완료!");
				} catch (Exception e) {
					System.out.println("오류가발생" + e.toString());
				} finally {
					try {
						oos.close();
					} catch (IOException e) {
						System.out.println("oos.close() 에러발생" + e.toString());
					}
				}
				break;
			case 2:
				System.out.println("프로필을 선택하세요.");
				System.out.print("1. " + userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList())
						+ ", 2. " + userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()));
				int password = sc.nextInt();
				sc.nextLine();
				for (;;) {
					if (password == 1) {
						System.out.print("새로운 비밀번호를 입력하세요 : ");
						newName = sc.nextLine();
						List<Integer> str = userStream.stream().map(s -> s.getProfilePassword1())
								.collect(Collectors.toList());
						int oN = str.getFirst();
						userList.stream().filter(s -> s.getProfilePassword1() == oN)
								.forEach(s -> s.setProfilePassword1(newPassword));
						break;
					} else if (password == 2) {
						System.out.print("새로운 비밀번호를 입력하세요 : ");
						newName = sc.nextLine();
						List<Integer> str = userStream.stream().map(s -> s.getProfilePassword2())
								.collect(Collectors.toList());
						int oN = str.getFirst();
						userList.stream().filter(s -> s.getProfilePassword2() == oN)
								.forEach(s -> s.setProfilePassword1(newPassword));
						break;
					} else {
						System.out.println("잘못 입력했습니다. 다시 입력하세요.");
					}
				}
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("User.txt"))));
					oos.writeObject(userList);
					System.out.println("변경 완료!");
				} catch (Exception e) {
					System.out.println("오류가발생" + e.toString());
				} finally {
					try {
						oos.close();
					} catch (IOException e) {
						System.out.println("oos.close() 에러발생" + e.toString());
					}
				}
				break;
			default:
				System.out.println("잘못 입력했습니다. ");
				break;
			}
		}
	}

	private static int loginMenu2(List<User> userStream) {
		User user = new User();
		System.out.println("메뉴를 선택하세요.");
		System.out
				.println("현재 프로필 : " + userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()));
		System.out.println("1. 프로필 선택, 2. 계정 관리");
		System.out.println("3. 검색, 4. 오늘의 TOP 5");
		System.out.println("5. 카테고리별 검색, 6. 국가별 검색");
		System.out.println("7. 내가 찜한 목록, 8. 다운로드 목록");
		System.out.println("9. 시청 목록, 10. 고객센터   ");
		System.out.print("11. 로그아웃   ");
		int number = sc.nextInt();
		return number;
	}

	private static int profileChange(List<User> userStream) {
		User user = new User();
		boolean userFlag = false;
		boolean match = false;
		System.out.println("사용할 프로필을 선택하세요.");
		System.out.print("1. " + userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList()) + " 2. "
				+ userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()) + "  ");
		int number = sc.nextInt();
		List<Integer> intStream = null;
		while (!userFlag) {
			System.out.print("프로필 비밀번호를 입력하세요 : ");
			int userPassword = sc.nextInt();
			switch (number) {
			case 1:
				intStream = userStream.stream().map(s -> s.getProfilePassword1()).collect(Collectors.toList());
				match = intStream.stream().anyMatch(s -> s.equals(userPassword));
				if (match == true) {
					userFlag = true;
					System.out.println("프로필 변경 완료");
				}
				break;
			case 2:
				intStream = userStream.stream().map(s -> s.getProfilePassword2()).collect(Collectors.toList());
				match = intStream.stream().anyMatch(s -> s.equals(userPassword));
				if (match == true) {
					userFlag = true;
					System.out.println("프로필 변경 완료");
				}
				break;
			default:
				System.out.println("로그인 정보가 맞지 않습니다. 다시 입력하세요.");
				break;
			}
		}
		return number;
	}

	private static List<User> userLogin(ArrayList<User> userList) {
		User user = new User();
		boolean userFlag = false;
		List<User> userStream = null;
		for (;;) {
			sc.nextLine();
			System.out.print("사용자 ID를 입력하세요 : ");
			String userID = sc.nextLine();
			System.out.print("사용자 비밀번호를 입력하세요 : ");
			int userPassword = sc.nextInt();
			for (int i = 0; i < userList.size(); i++) {
				if (userID.equals(userList.get(i).getId()) && userPassword == userList.get(i).getPassword()) {
					userFlag = true;
					System.out.println("로그인 성공");
					break;
				}
			}
			if (userFlag == true) {
				userStream = userList.stream()
						.filter(s -> s.getId().equals(userID) && (s.getPassword() == userPassword))
						.collect(Collectors.toList());
				break;
			} else {
				System.out.println("로그인 정보가 맞지 않습니다. 다시 입력하세요.");
			}
		}
		return userStream;
	}

	private static void printUser(ArrayList<User> userList) {
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
		}
	}

	private static void loadFile(ArrayList<User> userList) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("User.txt"));
			String userName = null;
			String[] readUser = new String[9];
			while ((userName = br.readLine()) != null) {
				readUser[0] = userName;
				readUser[1] = br.readLine();
				readUser[2] = br.readLine();
				readUser[3] = br.readLine();
				readUser[4] = br.readLine();
				readUser[5] = br.readLine();
				readUser[6] = br.readLine();
				readUser[7] = br.readLine();
				readUser[8] = br.readLine();
				User user = new User(readUser[0], readUser[1], Integer.parseInt(readUser[2]), readUser[3], readUser[4],
						Integer.parseInt(readUser[5]), readUser[6], Integer.parseInt(readUser[7]), readUser[8]);
				userList.add(user);
			}
			br.close();
		} catch (NumberFormatException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int loginMenu(List<User> userStream) {
		User user = new User();
		System.out.println("메뉴를 선택하세요.");
		System.out
				.println("현재 프로필 : " + userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList()));
		System.out.println("1. 프로필 선택, 2. 계정 관리");
		System.out.println("3. 검색, 4. 오늘의 TOP 5");
		System.out.println("5. 카테고리별 검색, 6. 국가별 검색");
		System.out.println("7. 내가 찜한 목록, 8. 다운로드 목록");
		System.out.println("9. 시청 목록, 10. 고객센터  ");
		System.out.print("11. 로그아웃   ");
		int number = sc.nextInt();
		return number;
	}

}
