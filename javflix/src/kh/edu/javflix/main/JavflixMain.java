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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import kh.edu.javflix.cart.Cart;
import kh.edu.javflix.ott.Ott;
import kh.edu.javflix.user.User;

public class JavflixMain {
	public static Scanner sc = new Scanner(System.in);
	public static String newName = null;
	public static String newPassword = null;
	public static String newAccountPassword = null;
	static Cart cart = new Cart();
	public static String ottNumber = null;
	public static List<Integer> numCheck = null;
	public static String oN = null;
	static User user = new User();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("");
		System.out.println("");
		System.out.println("                   Welcome to Javflix!");
		System.out.println("");
		System.out.println("");
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		boolean flag = false;
		ArrayList<User> userList = new ArrayList<>();
		List<User> userStream = null;
		ArrayList<Ott> ottList = new ArrayList<>();
		ArrayList<Ott> cartList = new ArrayList<Ott>();
		loadFile(userList);
		printUser(userList);
		loadOtt(ottList);
		loadCart(ottList);
		while (!flag) {
			System.out.println("메뉴를 선택하세요.");
			System.out.print("1. 사용자 로그인 2. 관리자 로그인 3. 종료 ");
			String login = sc.nextLine();
			ottList.stream().forEach(s -> s.setViewer());
			switch (login) {
			case "1":
				userStream = userLogin(userList);
				flag = false;
				boolean menuFlag = false;
				while (!menuFlag) {
					String menu = loginMenu(userStream);
					switch (menu) {
					case "1":
						String number = profileChange(userStream);
						if (number.equals("2")) {
							boolean menuFlag2 = false;
							while (!menuFlag2) {
								menu = loginMenu2(userStream);
								switch (menu) {
								case "1":
									String number2 = profileChange(userStream);
									if (number2.equals("1"))
										menuFlag2 = true;
									break;
								case "2":
									accountManage(userList, userStream);
									break;
								case "3":
									cartList = printCart();
									search(ottList, userStream, cartList);
									break;
								case "4":
									cartList = printCart();
									top5(ottList, userStream, cartList);
									break;
								case "5":
									cartList = printCart();
									categorySearch(ottList, userStream, cartList);
									break;
								case "6":
									cartList = printCart();
									countrySearch(ottList, userStream, cartList);
									break;
								case "7":
									cartList = printCart();
									addList(cartList, userStream);
									break;
								case "8":
									cartList = printCart();
									searchDownLoad(cartList, userStream);
									break;
								case "9":
									cartList = printCart();
									searchSeen(cartList, userStream);
									break;
								case "10":
									cartList = printCart();
									customerService(userStream);
									break;
								case "11":
									menuFlag = true;
									break;
								default:
									System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
									break;
								}
							}
						}
						break;
					case "2":
						accountManage(userList, userStream);
						break;
					case "3":
						cartList = printCart();
						search(ottList, userStream, cartList);
						break;
					case "4":
						cartList = printCart();
						top5(ottList, userStream, cartList);
						break;
					case "5":
						cartList = printCart();
						categorySearch(ottList, userStream, cartList);
						break;
					case "6":
						cartList = printCart();
						countrySearch(ottList, userStream, cartList);
						break;
					case "7":
						cartList = printCart();
						addList(cartList, userStream);
						break;
					case "8":
						cartList = printCart();
						searchDownLoad(cartList, userStream);
						break;
					case "9":
						cartList = printCart();
						searchSeen(cartList, userStream);
						break;
					case "10":
						customerService(userStream);
						break;
					case "11":
						menuFlag = true;
						break;
					default:
						System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
						break;
					}
				}
				break;
			case "2":
				adminLogin(user);
				adminMenu(userStream, ottList);
				break;
			case "3":
				flag = true;
				System.out.println("종료되었습니다. 프로그램을 이용해주셔서 감사합니다. ");
				break;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
				break;
			}
		}
	}

	public static ArrayList<Ott> printCart() {
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

	public static void adminMenu(List<User> userStream, ArrayList<Ott> ottList) {
		boolean uuFlag = false;
		while (!uuFlag) {
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1. OTT 추가  2. OTT 제거");
			System.out.println("3. OTT 조회수 조회 4. 유저 관리");
			System.out.print("5. 고객센터   6. 로그아웃   ");
			ObjectOutputStream oos = null;
			String menu = sc.nextLine();
			switch (menu) {
			case "1":
				List<Integer> ottAdd = ottList.stream().map(s -> s.getNumber()).collect(Collectors.toList());
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
				Ott ott = new Ott("user", (ottAdd.getLast() + 1), newTitle, newCountry, newGenre, newStory, newActor,
						newDirector, "2024", newRate, newAge, "미추가", "미시청", "미다운로드", 1);
				ottList.add(ott);
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Ott.txt"))));
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
			case "2":
				cart.printOttStream(ottList);
				System.out.print("제거할 OTT 번호를 입력하세요 : ");
				String number = sc.nextLine();
				List<Ott> ottStream = ottList.stream().filter(s -> s.getNumber() != Integer.parseInt(number))
						.collect(Collectors.toList());
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
				ottStream.stream().filter(s -> s.getNumber() > Integer.parseInt(number))
						.forEach(s -> s.setNumber(s.getNumber() - 1));
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
			case "3":
				List<Ott> str = viewCheck(ottList);
				cart.printViewer(str);
				break;
			case "4":
				boolean userFlag = false;
				while (!userFlag) {
					System.out.println("메뉴를 선택하세요.");
					System.out.println("1. 유저 추가 2. 유저 제거");
					System.out.print("3. 유저 조회 4. 뒤로가기   ");
					String num = sc.nextLine();
					switch (num) {
					case "1":
						System.out.print("신규 사용자 이름을 입력하세요 : ");
						String newName = sc.nextLine();
						System.out.print("신규 사용자 ID를 입력하세요 : ");
						String newId = sc.nextLine();
						System.out.print("신규 사용자 비밀번호를 입력하세요 : ");
						String newPassword = sc.nextLine();
						System.out.print("신규 사용자 전화번호를 입력하세요 : ");
						String newPhone = sc.nextLine();
						System.out.print("신규 사용자 프로필 이름을 입력하세요 : ");
						String newProfileName = sc.nextLine();
						System.out.print("신규 사용자 프로필 비밀번호를 입력하세요 : ");
						String newProfilePassword = sc.nextLine();
						System.out.print("신규 사용자 멤버쉽 등급을 입력하세요 : ");
						String newMembership = sc.nextLine();
						User user = new User(newName, newId, Integer.parseInt(newPassword), newPhone, newProfileName,
								Integer.parseInt(newProfilePassword), null, 0, "silver");
						userStream.add(user);
						try {
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("User.txt"))));
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
					case "2":
						for (int i = 0; i < userStream.size(); i++) {
							user = userStream.get(i);
							System.out.print(user.getName() + " | ");
							System.out.print(user.getId() + " | ");
							System.out.print(user.getPhone() + " | ");
							System.out.print(user.getProfileName1() + " | ");
							System.out.print(user.getProfileName2() + " | ");
							System.out.print(user.getMembership() + " | ");
							System.out.println("");
						}
						System.out.print("제거할 사용자 ID를 입력하세요 : ");
						String userNum = sc.nextLine();
						List<User> stream = userStream.stream().filter(s -> !s.getId().equals(userNum))
								.collect(Collectors.toList());
						try {
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("User.txt"))));
							oos.writeObject(stream);
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
						break;
					case "3":
						for (int i = 0; i < userStream.size(); i++) {
							user = userStream.get(i);
							System.out.print(user.getName() + " | ");
							System.out.print(user.getId() + " | ");
							System.out.print(user.getPhone() + " | ");
							System.out.print(user.getProfileName1() + " | ");
							System.out.print(user.getProfileName2() + " | ");
							System.out.print(user.getMembership() + " | ");
							System.out.println("");
						}
						// 사용자 수와 멤버쉽 동향을 분석해 요금을 변경하거나 혜택을 변경하기 위해 조회함
						break;
					case "4":
						break;
					default:
						break;
					}
				}
				break;
			case "5":
//				ArrayList<QA> list = null;
//				ObjectInputStream ois = null;
//				try {
//					ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("User.txt"))));
//					list = (ArrayList<QA>) ois.readObject();
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						ois.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}

				// QA클래스와 QA.txt로 고객센터에 저장된 데이터를 입력해서 조회 및 답변
				break;
			case "6":
				uuFlag = true;
				break;
			default:
				System.out.println("잘못 입력했습니다. 다시 선택하세요.");
				break;
			}
		}
	}

	public static List<Ott> viewCheck(ArrayList<Ott> ottList) {
		List<Ott> topStream = ottList.stream().sorted(Comparator.comparing(Ott::getViewer).reversed())
				.collect(Collectors.toList());
		return topStream;
	}

	public static void top5(ArrayList<Ott> ottList, List<User> userStream, ArrayList<Ott> cartList)
			throws InterruptedException {
		List<Ott> topStream = ottList.stream().sorted(Comparator.comparing(Ott::getViewer).reversed())
				.collect(Collectors.toList());
		List<Ott> stream = topStream.stream().limit(5).collect(Collectors.toList());
		cart.printOttStream(stream);

		select(stream, userStream, cartList);
	}

	public static void adminLogin(User user) {
		for (;;) {
			System.out.print("관리자 ID를 입력하세요 : ");
			String adminId = sc.nextLine();
			System.out.print("관리자 비밀번호를 입력하세요 : ");
			String adminPassword = sc.nextLine();
			if (adminId.equals(user.getAdminid()) && adminPassword.equals(adminPassword)) {
				System.out.println("로그인 성공!");
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
			}
		}
	}

	public static void customerService(List<User> userStream) {
		for (;;) {
			System.out.println("Javflix 고객센터 전화번호 : 010-9054-1146 | 이메일 주소 : javparadise@gmail.com");
			System.out.print("Q&A 남기기 Y | N : ");
			String qA = sc.nextLine().toLowerCase();
			if (qA.equals("y")) {
				System.out.print("질문사항을 입력하세요 : (완료시 엔터)");
				String qAString = sc.nextLine();
				System.out.println(userStream.stream().map(s -> s.getName()).collect(Collectors.toList()) + "님의 질문사항 "
						+ qAString + "이 등록되었습니다. 빠른 시일 내로 답변드리겠습니다. ");
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("QA.txt"))));
					String qqA = "" + userStream.stream().map(s -> s.getName()).collect(Collectors.toList())
							+ "님의 질문사항 : " + qAString + "";
					oos.writeObject(qqA);
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
			} else if (qA.equals("n")) {
				break;
			} else {
				System.out.println("잘못 입력했습니다. ");
			}
		}
	}

	public static void countrySearch(ArrayList<Ott> ottList, List<User> userStream, ArrayList<Ott> cartList)
			throws InterruptedException {
		List<Ott> ottStream = null;
		boolean flag = false;
		while (!flag) {
			System.out.println("검색할 나라를 선택하세요.");
			System.out.print("1. 한국 2. 미국 3. 일본 4. 유럽 5. 스페인 6.뒤로가기  ");
			String number = sc.nextLine();
			switch (number) {
			case "1":
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("한국")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "2":
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("미국")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "3":
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("일본")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "4":
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("유럽")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "5":
				ottStream = ottList.stream().filter(s -> s.getCountry().equals("스페인")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "6":
				flag = true;
				break;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
				break;
			}
		}
	}

	public static void categorySearch(ArrayList<Ott> ottList, List<User> userStream, ArrayList<Ott> cartList)
			throws InterruptedException {
		List<Ott> ottStream = null;
		boolean flag = false;
		while (!flag) {
			System.out.println("검색할 카테고리를 선택하세요.");
			System.out.print("1. 로맨스 2. 드라마, 3. 액션 4. 애니메이션 5. 범죄 6. 스릴러 7. 코미디 8. 뒤로가기  ");
			String number = sc.nextLine();
			switch (number) {
			case "1":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("로맨스")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "2":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("드라마")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "3":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("액션")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "4":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("애니메이션")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "5":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("범죄")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "6":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("스릴러")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "7":
				ottStream = ottList.stream().filter(s -> s.getGenre().equals("코미디")).collect(Collectors.toList());
				cart.printOttStream(ottStream);
				select(ottStream, userStream, cartList);
				break;
			case "8":
				flag = true;
				break;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
				break;
			}
		}
	}

	public static void select(List<Ott> ottStream, List<User> userStream, ArrayList<Ott> cartList)
			throws InterruptedException {
		ObjectOutputStream oos = null;
		String[] story = null;
		List<Ott> list = null;
		boolean check = false;
		List<String> str = null;
		String numSelect = null;
		List<Ott> checkList = null;
		boolean check1 = false;
		System.out.print("OTT 번호를 선택하세요 : ");
		ottNumber = sc.nextLine();
		List<Ott> ottList = ottStream.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
				.collect(Collectors.toList());
		cart.printOttStream(ottList);
		for (;;) {
			System.out.println("메뉴를 선택하세요.");
			System.out.print("1. 시청 2. 찜하기 3. 다운로드 4. 뒤로가기  ");
			numSelect = sc.nextLine();
			if (numSelect.equals("1")) {
				story = ottList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
						.map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
				for (String data : story) {
					System.out.print(data.toString());
					Thread.sleep(100);
				}
				Thread.sleep(800);
				System.out.println("\n시청 완료!");
				str = userStream.stream().filter(s -> !s.getProfileName1().equals("user")).map(s -> s.getProfileName1())
						.collect(Collectors.toList());
				oN = str.getLast();
				try {
					cartList.stream().filter(s -> s.getUser().equals(oN))
							.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
							.forEach(s -> s.setIsSeen("시청 완료"));
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
					oos.writeObject(cartList);
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
			} else if (numSelect.equals("2")) {
				str = userStream.stream().filter(s -> !s.getProfileName1().equals("user")).map(s -> s.getProfileName1())
						.collect(Collectors.toList());
				oN = str.getLast();
				check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
				if (check == true) {
					checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
							.filter(s -> s.getIsAdd().equals("추가 완료")).collect(Collectors.toList());
					check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check1 == false) {
						try {
							cartList.stream().filter(s -> s.getUser().equals(oN))
									.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.forEach(s -> s.setIsAdd("추가 완료"));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
							System.out.println("찜 목록에 추가되었습니다. ");
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
						System.out.println("이미 찜하신 OTT입니다. ");
					}
				} else {
					try {
						cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
								.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsAdd("추가 완료"));
						cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(cartList);
						System.out.println("찜 목록에 추가되었습니다. ");
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
				break;
			} else if (numSelect.equals("3")) {
				str = userStream.stream().filter(s -> !s.getProfileName1().equals("user")).map(s -> s.getProfileName1())
						.collect(Collectors.toList());
				oN = str.getLast();
				check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
				if (check == true) {
					checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
							.filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
					check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check1 == false) {
						try {
							cartList.stream().filter(s -> s.getUser().equals(oN))
									.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.forEach(s -> s.setIsDown("다운로드 완료"));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
							System.out.print("다운로드 진행 중...");
							for (int i = 0; i < 10; i++) {
								System.out.print(i * 10 + "%..");
								Thread.sleep(200);
							}
							Thread.sleep(800);
							System.out.println("다운로드 완료!");
							List<Ott> li = cartList.stream().filter(s -> s.getUser().equals(oN))
									.collect(Collectors.toList());
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
						System.out.println("이미 다운받은 OTT입니다. ");
					}
				} else {
					try {
						cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
								.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsDown("다운로드 완료"));
						cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));
						oos = new ObjectOutputStream(
								new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
						oos.writeObject(cartList);
						System.out.print("다운로드 진행 중...");
						for (int i = 0; i < 10; i++) {
							System.out.print(i * 10 + "%..");
							Thread.sleep(200);
						}
						Thread.sleep(800);
						System.out.println("다운로드 완료!");
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
				break;
			} else if (numSelect.equals("4")) {
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}

	public static void addList(ArrayList<Ott> cartList, List<User> userStream) throws InterruptedException {
		List<String> str = null;
		str = userStream.stream().filter(s -> !s.getProfileName1().equals("user")).map(s -> s.getProfileName1())
				.collect(Collectors.toList());
		oN = str.getLast();
		List<Ott> addList = cartList.stream().filter(s -> s.getUser().equals(oN)).collect(Collectors.toList());
		boolean check = addList.stream().anyMatch(s -> s.getIsAdd().equals("추가 완료"));
		if (check == true) {
			List<Ott> list = addList.stream().filter(s -> s.getIsAdd().equals("추가 완료"))
					.filter(s -> s.getUser().equals(oN)).collect(Collectors.toList());
			ObjectOutputStream oos = null;
			String[] story = null;
			List<Ott> ottStream = null;
			List<Integer> numCheck = null;
			cart.printOttStream(list);
			boolean flag = false;
			while (!flag) {
				check = list.stream().anyMatch(s -> s.getIsAdd().equals("추가 완료"));
				if (check == false) {
					System.out.println("찜 목록이 비었습니다. ");
					flag = true;
				} else {
					System.out.println("메뉴를 선택하세요.");
					System.out.print("1. 시청 2. 목록 삭제 3. 다운로드 4. 뒤로가기  ");
					String number = sc.nextLine();
					switch (number) {
					case "1":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							story = list.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst()
									.split("");
							for (String data : story) {
								System.out.print(data.toString());
								Thread.sleep(100);
							}
							Thread.sleep(800);
							System.out.println("\n시청 완료!");
							str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
									.map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getLast();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsSeen("시청 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
							System.out.print("시청할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							story = list.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
							for (String data : story) {
								System.out.print(data.toString());
								Thread.sleep(100);
							}
							Thread.sleep(800);
							System.out.println("\n시청 완료!");
							str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getFirst();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsSeen("시청 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
						break;
					case "2":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							cartList.stream().filter(s -> s.getIsAdd().equals("추가 완료"))
									.filter(s -> s.getUser().equals(oN)).forEach(s -> s.setIsAdd("미추가"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("찜 목록에서 삭제 완료");
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
							System.out.print("제거할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getIsAdd().equals("추가 완료")).filter(s -> s.getUser().equals(oN))
									.forEach(s -> s.setIsAdd("미추가"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("찜 목록에서 삭제 완료");
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

						break;
					case "3":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							try {
								cartList.stream().filter(s -> s.getIsDown().equals("미다운로드"))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.filter(s -> s.getUser().equals(oN)).forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								Thread.sleep(800);
								System.out.println("다운로드 완료!");
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
							System.out.print("다운받을 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getIsDown().equals("미다운로드")).filter(s -> s.getUser().equals(oN))
									.forEach(s -> s.setIsDown("다운로드 완료"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								Thread.sleep(800);
								System.out.println("다운로드 완료!");
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
						break;
					case "4":
						flag = true;
						break;
					default:
						break;
					}
				}
			}
		} else {
			System.out.println("찜 목록이 비었습니다. ");
		}
	}

	public static void searchSeen(ArrayList<Ott> cartList, List<User> userStream) throws InterruptedException {
		List<String> str = null;
		str = userStream.stream().filter(s -> !s.getProfileName1().equals("user")).map(s -> s.getProfileName1())
				.collect(Collectors.toList());
		oN = str.getLast();
		List<Ott> checkList = null;
		boolean check1 = false;
		List<Ott> seenList = cartList.stream().filter(s -> s.getUser().equals(oN)).collect(Collectors.toList());
		boolean check = seenList.stream().anyMatch(s -> s.getIsSeen().equals("시청 완료"));
		if (check == true) {
			List<Ott> list = seenList.stream().filter(s -> s.getIsSeen().equals("시청 완료"))
					.filter(s -> s.getUser().equals(oN)).collect(Collectors.toList());
			ObjectOutputStream oos = null;
			String[] story = null;
			List<Ott> ottStream = null;
			List<Integer> numCheck = null;
			cart.printOttStream(list);
			boolean flag = false;
			while (!flag) {
				check = list.stream().anyMatch(s -> s.getIsSeen().equals("시청 완료"));
				if (check == false) {
					System.out.println("시청 목록이 비었습니다. ");
					flag = true;
				} else {
					System.out.println("메뉴를 선택하세요.");
					System.out.print("1. 시청 2. 목록 삭제 3. 찜하기 4. 다운로드 5. 뒤로가기 ");
					String number = sc.nextLine();
					switch (number) {
					case "1":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							story = list.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst()
									.split("");
							for (String data : story) {
								System.out.print(data.toString());
								Thread.sleep(100);
							}
							Thread.sleep(800);
							System.out.println("\n시청 완료!");
							str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
									.map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getLast();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsSeen("시청 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
							System.out.print("시청할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							story = list.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
							for (String data : story) {
								System.out.print(data.toString());
								Thread.sleep(100);
							}
							Thread.sleep(800);
							System.out.println("\n시청 완료!");
							str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getFirst();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsSeen("시청 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
						break;
					case "2":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							cartList.stream().filter(s -> s.getIsSeen().equals("시청 완료"))
									.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals(oN)).forEach(s -> s.setIsSeen("미시청"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("시청 목록에서 삭제 완료");
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
							System.out.print("제거할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getIsSeen().equals("시청 완료")).filter(s -> s.getUser().equals(oN))
									.forEach(s -> s.setIsSeen("미시청"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("시청 목록에서 삭제 완료");
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

						break;
					case "3":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
									.map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getLast();
							check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
							if (check == true) {
								checkList = cartList.stream().filter(s -> s.getIsAdd().equals("추가 완료"))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.collect(Collectors.toList());
								check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
								if (check1 == false) {
									try {
										cartList.stream().filter(s -> s.getUser().equals(oN))
												.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
												.forEach(s -> s.setIsAdd("추가 완료"));
										oos = new ObjectOutputStream(
												new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
										oos.writeObject(cartList);
										System.out.println("찜 목록에 추가되었습니다. ");
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
									System.out.println("이미 찜하신 OTT입니다. ");
								}
							} else {
								try {
									cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
											.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsAdd("추가 완료"));
									cartList.stream().filter(s -> s.getUser().equals("user"))
											.forEach(s -> s.setUser(oN));
									oos = new ObjectOutputStream(
											new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
									oos.writeObject(cartList);
									System.out.println("찜 목록에 추가되었습니다. ");
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
						} else {
							System.out.print("찜할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsAdd("추가 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("찜 목록에 추가되었습니다. ");
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
						break;
					case "4":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							try {
								cartList.stream().filter(s -> s.getIsDown().equals("미다운로드"))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.filter(s -> s.getUser().equals(oN)).forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								Thread.sleep(800);
								System.out.println("다운로드 완료!");
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
							System.out.print("다운받을 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getIsDown().equals("미다운로드")).filter(s -> s.getUser().equals(oN))
									.forEach(s -> s.setIsDown("다운로드 완료"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								Thread.sleep(800);
								System.out.println("다운로드 완료!");
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
						break;
					case "5":
						flag = true;
						break;
					default:
						break;
					}
				}
			}
		} else {
			System.out.println("시청 목록이 비었습니다. ");
		}
	}

	public static void searchDownLoad(ArrayList<Ott> cartList, List<User> userStream) throws InterruptedException {
		List<String> str = null;
		str = userStream.stream().filter(s -> !s.getProfileName1().equals("user")).map(s -> s.getProfileName1())
				.collect(Collectors.toList());
		oN = str.getLast();
		List<Ott> checkList = null;
		boolean check1 = false;
		List<Ott> downList = cartList.stream().filter(s -> s.getUser().equals(oN)).collect(Collectors.toList());
		boolean check = downList.stream().anyMatch(s -> s.getIsDown().equals("다운로드 완료"));
		if (check == true) {
			List<Ott> list = downList.stream().filter(s -> s.getIsDown().equals("다운로드 완료"))
					.filter(s -> s.getUser().equals(oN)).collect(Collectors.toList());
			ObjectOutputStream oos = null;
			String[] story = null;
			List<Ott> ottStream = null;
			List<Integer> numCheck = null;
			cart.printOttStream(list);
			boolean flag = false;
			while (!flag) {
				check = list.stream().anyMatch(s -> s.getIsDown().equals("다운로드 완료"));
				if (check == false) {
					System.out.println("다운로드 목록이 비었습니다. ");
					flag = true;
				} else {
					System.out.println("메뉴를 선택하세요.");
					System.out.print("1. 시청 2. 목록 삭제 3. 찜하기 4. 뒤로가기 ");
					String number = sc.nextLine();
					switch (number) {
					case "1":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							story = list.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst()
									.split("");
							for (String data : story) {
								System.out.print(data.toString());
								Thread.sleep(100);
							}
							Thread.sleep(200);
							System.out.println("\n시청 완료!");
							str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
									.map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getLast();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsSeen("시청 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
							System.out.print("시청할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							story = list.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
							for (String data : story) {
								System.out.print(data.toString());
								Thread.sleep(100);
							}
							Thread.sleep(200);
							System.out.println("\n시청 완료!");
							str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getFirst();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsSeen("시청 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
						break;
					case "2":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							cartList.stream().filter(s -> s.getIsDown().equals("다운로드 완료"))
									.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals(oN)).forEach(s -> s.setIsDown("미다운로드"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("다운로드 목록에서 삭제 완료!");
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
							System.out.print("제거할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getIsDown().equals("다운로드 완료")).filter(s -> s.getUser().equals(oN))
									.forEach(s -> s.setIsDown("미다운로드"));
							try {
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("다운로드 목록에서 삭제 완료!");
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

						break;
					case "3":
						numCheck = list.stream().map(s -> s.getNumber()).collect(Collectors.toList());
						if (numCheck.getFirst() == numCheck.getLast()) {
							ottNumber.equals(numCheck.getFirst());
							str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
									.map(s -> s.getProfileName1()).collect(Collectors.toList());
							oN = str.getLast();
							check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
							if (check == true) {
								checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.filter(s -> s.getIsAdd().equals("추가 완료")).collect(Collectors.toList());
								check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
								if (check1 == false) {
									try {
										cartList.stream().filter(s -> s.getUser().equals(oN))
												.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
												.forEach(s -> s.setIsAdd("추가 완료"));
										oos = new ObjectOutputStream(
												new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
										oos.writeObject(cartList);
										System.out.println("찜 목록에 추가되었습니다. ");
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
									System.out.println("이미 찜하신 OTT입니다. ");
								}
							} else {
								try {
									cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
											.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsAdd("추가 완료"));
									cartList.stream().filter(s -> s.getUser().equals("user"))
											.forEach(s -> s.setUser(oN));
									oos = new ObjectOutputStream(
											new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
									oos.writeObject(cartList);
									System.out.println("찜 목록에 추가되었습니다. ");
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
						} else {
							System.out.print("찜할 OTT 번호를 선택하세요 : ");
							ottNumber = sc.nextLine();
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsAdd("추가 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.println("찜 목록에 추가되었습니다. ");
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

						break;
					case "4":
						flag = true;
						break;
					default:
						break;
					}
				}
			}
		} else {
			System.out.println("다운로드 목록이 비었습니다. ");
		}
	}

	public static void loadCart(ArrayList<Ott> ottList) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
			oos.writeObject(ottList);
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

	public static void loadOtt(ArrayList<Ott> ottList) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Ott.txt"));
			String ottName = null;
			String[] readOtt = new String[15];
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
				readOtt[13] = br.readLine();
				readOtt[14] = br.readLine();
				Ott ott = new Ott(readOtt[0], Integer.parseInt(readOtt[1]), readOtt[2], readOtt[3], readOtt[4],
						readOtt[5], readOtt[6], readOtt[7], readOtt[8], readOtt[9], readOtt[10], readOtt[11],
						readOtt[12], readOtt[13], Integer.parseInt(readOtt[14]));
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

	public static void search(ArrayList<Ott> ottList, List<User> userStream, ArrayList<Ott> cartList)
			throws InterruptedException {
		cart.printOttList(ottList);
		List<Ott> ottStream = null;
		String numSelect = null;
		ObjectOutputStream oos = null;
		BufferedWriter bw = null;
		String[] story = null;
		List<String> str = null;
		boolean check = false;
		boolean flag = false;
		boolean check1 = false;
		List<Ott> checkList = null;
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. OTT 선택 2. 검색 3. 뒤로가기  ");
		String menu = sc.nextLine();
		switch (menu) {
		case "1":
			System.out.print("OTT 번호를 선택하세요 : ");
			ottNumber = sc.nextLine();
			ottStream = ottList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
					.collect(Collectors.toList());
			cart.printOttStream(ottStream);
			System.out.print("1. 시청 2. 찜하기 3. 다운로드 4. 뒤로가기  ");
			numSelect = sc.nextLine();
			for (;;) {
				if (numSelect.equals("1")) {
					story = ottStream.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
					for (String data : story) {
						System.out.print(data.toString());
						Thread.sleep(100);
					}
					Thread.sleep(800);
					System.out.println("\n시청 완료!");
					String a = "               ZZZZ      ZZZZZ     zZZ     ZZZ      WZZZZZZZZZZ  ZZZZ         ZZZZZ    DZZZZ    ZZZZ    ";
					String b = "               ZzZz     ZZZ ZZZ    ZZZ     ZZZ      WZzZZZZZZZZ  ZZZZ          ZzZ      EZ9Z   EZEZ      ";
					String c = "               ZzZz    ZZZ   ZZZ   ZZZ     EzZ      jZzZ         ZZZZ          ZzZ      ZZZZ  ZEZB      ";
					String d = "               zZzZ    ZZZ   ZZZ   ZzZ     ZzZ      ZzZZ         ZZZZ          ZEE       ZEZ5ZEZZ      ";
					String t = "               ZzZz    zZz   ZZZ   ZZZ     ZzZ      jZzZ         ZZZZ          ZzZ       8Z999ZZ       ";
					String f = "               ZzZz    zZz   ZZZ   ZZZ     ZzZ      WZzEZZZZZ9   ZZZZ          ZzZ        ZEz8Z        ";
					String g = "               ZzZz    zZzZZZZZZ   ZZZ     ZzZ      WZzEZZZZZE   ZZZZ          ZzZ        ,Ez8E        ";
					String h = "               ZzZz    zZzZZZZZZ   ZzZ     ZZZ      WZzZ         ZZZZ          ZzZ       ZEz8ZZ        ";
					String i = "               8zZz    zZz   ZZZ   ZZZ     ZzZ      WZzZ         ZZZZ          ZzZ      ZEEZ 99Z      ";
					String j = "       ZZZZ    zzZz    zZz   ZZZ   ZZZ     ZzZ      WZzZ         ZZZZ          ZzZ     ZEEZ   9ZEZ    ";
					String k = "        ZZZ    zzZz    zZz   ZZZ    ZZZ   ZZZ       ZZZZ         ZZZZ          ZzZ     ZEEZ    wZEZD   ";
					String l = "         ZZZzzZ8zZ     ZZZ   ZZZ     ZZZ ZZZ        8ZZZ         ZZZZ          ZZZ    ZZZZZ     ZZZZZ   ";
					String m = "          ZZZZZZZ      ZZZ   ZZZ      ZZZZZ         ZZZZ         ZZZZZZZZZZZ  ZZZZZ   ZZZZZ      ZZZZZ  ";

					System.out.print(a);
					Thread.sleep(60);
					System.out.print("\n" + b);
					Thread.sleep(60);
					System.out.print("\n" + c);
					Thread.sleep(60);
					System.out.print("\n" + d);
					Thread.sleep(60);
					System.out.print("\n" + t);
					Thread.sleep(60);
					System.out.print("\n" + f);
					Thread.sleep(60);
					System.out.print("\n" + g);
					Thread.sleep(60);
					System.out.print("\n" + h);
					Thread.sleep(60);
					System.out.print("\n" + i);
					Thread.sleep(60);
					System.out.print("\n" + j);
					Thread.sleep(60);
					System.out.print("\n" + k);
					Thread.sleep(60);
					System.out.print("\n" + l);
					Thread.sleep(60);
					System.out.print("\n" + m + "\n");
					Thread.sleep(60);

					str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
							.map(s -> s.getProfileName1()).collect(Collectors.toList());
					oN = str.getLast();
					check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check == false) {
						try {
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsSeen("시청 완료"));
							cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));

							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
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
						try {
							cartList.stream().filter(s -> s.getUser().equals(oN))
									.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.forEach(s -> s.setIsSeen("시청 완료"));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
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
					break;
				} else if (numSelect.equals("2")) {
					str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
							.map(s -> s.getProfileName1()).collect(Collectors.toList());
					oN = str.getLast();
					check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check == true) {
						checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
								.filter(s -> s.getIsAdd().equals("추가 완료")).collect(Collectors.toList());
						check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
						if (check1 == false) {
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsAdd("추가 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
							System.out.println("이미 찜하신 OTT입니다. ");
							flag = true;
						}
					} else {
						try {
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsAdd("추가 완료"));
							cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
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
					if (flag == false) {
						System.out.print("찜 목록에 추가되었습니다. 다운로드하시겠습니까? (Y | N)  ");
						String yN = sc.nextLine().toLowerCase();
						for (;;) {
							if (yN.equals("y")) {
								checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
								check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
								if (check1 == false) {
									try {
										cartList.stream().filter(s -> s.getUser().equals(oN))
												.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
												.forEach(s -> s.setIsDown("다운로드 완료"));
										oos = new ObjectOutputStream(
												new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
										oos.writeObject(cartList);
										System.out.print("다운로드 진행 중...");
										for (int i = 0; i < 10; i++) {
											System.out.print(i * 10 + "%..");
											Thread.sleep(200);
										}
										Thread.sleep(800);
										System.out.println("다운로드 완료!");
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
									System.out.println("이미 다운받은 OTT입니다. ");
								}
								break;
							} else if (yN.equals("n")) {
								break;
							} else {
								System.out.println("잘못 입력했습니다. 다시 입력하세요.");
							}
						}
					} else {
						break;
					}
					break;
				} else if (numSelect.equals("3")) {
					str = userStream.stream().filter(s -> !s.getProfileName1().equals("user"))
							.map(s -> s.getProfileName1()).collect(Collectors.toList());
					oN = str.getLast();
					check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check == true) {
						checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
								.filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
						check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
						if (check1 == false) {
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								Thread.sleep(800);
								System.out.println("다운로드 완료!");
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
							System.out.println("이미 다운받은 OTT입니다. ");
							flag = true;
						}
					} else {
						try {
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsDown("다운로드 완료"));
							cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
							System.out.print("다운로드 진행 중...");
							for (int i = 0; i < 10; i++) {
								System.out.print(i * 10 + "%..");
								Thread.sleep(200);
							}
							Thread.sleep(800);
							System.out.println("다운로드 완료!");
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
					break;
				} else {
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
			}
			break;
		case "2":
			System.out.print("검색할 키워드를 입력하세요 : ");
			String keyWord = sc.nextLine();
			ottStream = ottList.stream().filter(s -> s.getCountry().equals(keyWord) || s.getTitle().equals(keyWord)
					|| s.getStory().equals(keyWord) || s.getActor().equals(keyWord) || s.getDirector().equals(keyWord)
					|| s.getYear().equals(keyWord) || s.getAge().equals(keyWord)).collect(Collectors.toList());
			cart.printOttStream(ottStream);
			System.out.print("OTT 번호를 선택하세요 : ");
			ottNumber = sc.nextLine();
			List<Ott> list = ottList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
					.collect(Collectors.toList());
			cart.printOttStream(list);
			for (;;) {
			System.out.print("1. 시청 2. 찜하기 3. 다운로드 ");
			numSelect = sc.nextLine();
				if (numSelect.equals("1")) {
					story = list.stream().map(s -> s.getStory()).collect(Collectors.toList()).getFirst().split("");
					for (String data : story) {
						System.out.print(data.toString());
						Thread.sleep(100);
					}
					Thread.sleep(800);
					System.out.println("\n시청 완료!");
					str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
					oN = str.getFirst();
					check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check == false) {
						try {
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsSeen("시청 완료"));
							cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));

							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
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
						try {
							cartList.stream().filter(s -> s.getUser().equals(oN))
									.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.forEach(s -> s.setIsSeen("시청 완료"));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
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
					break;
				} else if (numSelect.equals("2")) {
					str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
					oN = str.getFirst();
					check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check == true) {
						checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
								.filter(s -> s.getIsAdd().equals("추가 완료")).collect(Collectors.toList());
						check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
						if (check1 == false) {
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsAdd("추가 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
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
							System.out.println("이미 찜하신 OTT입니다. ");
							flag = true;
						}
					} else {
						try {
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsAdd("추가 완료"));
							cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));

							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
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
					if (flag = false) {
						System.out.print("찜 목록에 추가되었습니다. 다운로드하시겠습니까? (Y | N)  ");
						String yN = sc.nextLine().toLowerCase();
						for (;;) {
							if (yN.equals("y")) {
								checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
								check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
								if (check1 == false) {
									str = userStream.stream().map(s -> s.getProfileName1())
											.collect(Collectors.toList());
									oN = str.getFirst();
									try {
										cartList.stream().filter(s -> s.getUser().equals(oN))
												.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
												.forEach(s -> s.setIsDown("다운로드 완료"));
										oos = new ObjectOutputStream(
												new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
										oos.writeObject(cartList);
										System.out.print("다운로드 진행 중...");
										for (int i = 0; i < 10; i++) {
											System.out.print(i * 10 + "%..");
											Thread.sleep(200);
										}
										Thread.sleep(800);
										System.out.println("다운로드 완료!");
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
									System.out.println("이미 다운받은 OTT입니다. ");
								}
								break;
							} else if (yN.equals("n")) {
								break;
							} else {
								System.out.println("잘못 입력했습니다. 다시 입력하세요.");
							}
						}
					} else {
						break;
					}
					break;
				} else if (numSelect.equals("3")) {
					str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
					oN = str.getFirst();
					check = cartList.stream().anyMatch(s -> s.getUser().equals(oN));
					if (check == true) {
						checkList = cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
								.filter(s -> s.getIsDown().equals("다운로드 완료")).collect(Collectors.toList());
						check1 = checkList.stream().anyMatch(s -> s.getUser().equals(oN));
						if (check1 == false) {
							try {
								cartList.stream().filter(s -> s.getUser().equals(oN))
										.filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
										.forEach(s -> s.setIsDown("다운로드 완료"));
								oos = new ObjectOutputStream(
										new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
								oos.writeObject(cartList);
								System.out.print("다운로드 진행 중...");
								for (int i = 0; i < 10; i++) {
									System.out.print(i * 10 + "%..");
									Thread.sleep(200);
								}
								Thread.sleep(800);
								System.out.println("다운로드 완료!");
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
							System.out.println("이미 다운받은 OTT입니다. ");
							flag = true;
						}
					} else {
						try {
							cartList.stream().filter(s -> s.getNumber() == Integer.parseInt(ottNumber))
									.filter(s -> s.getUser().equals("user")).forEach(s -> s.setIsDown("다운로드 완료"));
							cartList.stream().filter(s -> s.getUser().equals("user")).forEach(s -> s.setUser(oN));
							oos = new ObjectOutputStream(
									new BufferedOutputStream(new FileOutputStream(new File("Cart.txt"))));
							oos.writeObject(cartList);
							System.out.print("다운로드 진행 중...");
							for (int i = 0; i < 10; i++) {
								System.out.print(i * 10 + "%..");
								Thread.sleep(200);
							}
							Thread.sleep(800);
							System.out.println("다운로드 완료!");
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
					break;
				} else {
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
				break;
			}
		case "3":
			break;
		default:
			System.out.println("잘못 입력했습니다. ");
			break;
		}

	}

	public static void accountManage(ArrayList<User> userList, List<User> userStream) {
		User user = new User();
		System.out.print("계정 비밀번호를 입력하세요 : ");
		String userPassword = sc.nextLine();
		List<User> stream = userStream.stream().filter(s -> s.getPassword() == Integer.parseInt(userPassword))
				.collect(Collectors.toList());
		if (stream != null) {
			System.out.println("본인 인증 성공!");
			System.out.println("변경할 정보를 선택하세요.");
			System.out.print("1. 프로필 이름 변경, 2. 프로필 비밀번호 변경 3. 계정 비밀번호 변경  ");
			String number = sc.nextLine();
			ObjectOutputStream oos = null;
			switch (number) {
			case "1":
				for (;;) {
					System.out.println("프로필을 선택하세요.");
					System.out.print("1. "
							+ userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList()) + ", 2. "
							+ userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()) + " ");
					String name = sc.nextLine();
					if (name.equals("1")) {
						System.out.print("새로운 이름을 입력하세요 : ");
						newName = sc.nextLine();
						List<String> str = userStream.stream().map(s -> s.getProfileName1())
								.collect(Collectors.toList());
						oN = str.getFirst();
						userList.stream().filter(s -> s.getProfileName1().equals(oN))
								.forEach(s -> s.setProfileName1(newName));
						break;
					} else if (name.equals("2")) {
						userList.stream().map(s -> s.getProfileName2()).collect(Collectors.toList());
						System.out.print("새로운 이름을 입력하세요 : ");
						newName = sc.nextLine();
						List<String> str = userStream.stream().map(s -> s.getProfileName2())
								.collect(Collectors.toList());
						oN = str.getFirst();
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
			case "2":
				for (;;) {
					System.out.println("프로필을 선택하세요.");
					System.out.print("1. "
							+ userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList()) + ", 2. "
							+ userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()) + " ");
					String password = sc.nextLine();
					if (password.equals("1")) {
						System.out.print("새로운 비밀번호를 입력하세요 : ");
						newPassword = sc.nextLine();
						List<Integer> str = userStream.stream().map(s -> s.getProfilePassword1())
								.collect(Collectors.toList());
						int oN1 = str.getFirst();
						userList.stream().filter(s -> s.getProfilePassword1() == oN1)
								.forEach(s -> s.setProfilePassword1(Integer.parseInt(newPassword)));
						break;
					} else if (password.equals("2")) {
						System.out.print("새로운 비밀번호를 입력하세요 : ");
						newPassword = sc.nextLine();
						List<Integer> str = userStream.stream().map(s -> s.getProfilePassword2())
								.collect(Collectors.toList());
						int oN1 = str.getFirst();
						userList.stream().filter(s -> s.getProfilePassword2() == oN1)
								.forEach(s -> s.setProfilePassword1(Integer.parseInt(newPassword)));
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
			case "3":
				System.out.print("새로운 비밀번호를 입력하세요 : ");
				newAccountPassword = sc.nextLine();

				List<String> str = userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList());
				oN = str.getFirst();
				userList.stream().filter(s -> s.getProfileName1().equals(oN))
						.forEach(s -> s.setPassword(Integer.parseInt(newAccountPassword)));
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
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
				break;
			}
		} else {
			System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
		}
	}

	public static String loginMenu2(List<User> userStream) {
		User user = new User();
		System.out.println("메뉴를 선택하세요.");
		System.out
				.println("현재 프로필 : " + userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()));
		System.out.println("1. 프로필 선택 2. 계정 관리");
		System.out.println("3. 검색 4. 오늘의 TOP 5");
		System.out.println("5. 카테고리별 검색 6. 국가별 검색");
		System.out.println("7. 내가 찜한 목록 8. 다운로드 목록");
		System.out.println("9. 시청 목록 10. 고객센터   ");
		System.out.print("11. 로그아웃   ");
		String number = sc.nextLine();
		return number;
	}

	public static String profileChange(List<User> userStream) {
		User user = new User();
		boolean userFlag = false;
		boolean match = false;
		String number = null;
		for (;;) {
			System.out.println("사용할 프로필을 선택하세요.");
			System.out.print("1. " + userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList())
					+ " 2. " + userStream.stream().map(s -> s.getProfileName2()).collect(Collectors.toList()) + "  ");
			number = sc.nextLine();
			List<Integer> intStream = null;
			if (number.equals("1") || number.equals("2")) {
				while (!userFlag) {
					System.out.print("프로필 비밀번호를 입력하세요 : ");
					String userPassword = sc.nextLine();
					switch (number) {
					case "1":
						intStream = userStream.stream().map(s -> s.getProfilePassword1()).collect(Collectors.toList());
						match = intStream.stream().anyMatch(s -> s == Integer.parseInt(userPassword));
						if (match == true) {
							userFlag = true;
							System.out.println("프로필 변경 완료!");
						}
						break;
					case "2":
						intStream = userStream.stream().map(s -> s.getProfilePassword2()).collect(Collectors.toList());
						match = intStream.stream().anyMatch(s -> s == Integer.parseInt(userPassword));
						if (match == true) {
							userFlag = true;
							System.out.println("프로필 변경 완료!");
						}
						break;
					default:
						System.out.println("로그인 정보가 맞지 않습니다. 다시 입력하세요.");
						break;
					}
				}
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요. ");
			}
		}
		return number;
	}

	public static List<User> userLogin(ArrayList<User> userList) {
		User user = new User();
		boolean userFlag = false;
		List<User> userStream = null;
		for (;;) {
			System.out.print("사용자 ID를 입력하세요 : ");
			String userID = sc.nextLine();
			System.out.print("사용자 비밀번호를 입력하세요 : ");
			String userPassword = sc.nextLine();
			for (int i = 0; i < userList.size(); i++) {
				if (userID.equals(userList.get(i).getId())
						&& Integer.parseInt(userPassword) == userList.get(i).getPassword()) {
					userFlag = true;
					System.out.println("로그인 성공!");
					break;
				}
			}
			if (userFlag == true) {
				userStream = userList.stream()
						.filter(s -> s.getId().equals(userID) && (s.getPassword() == Integer.parseInt(userPassword)))
						.collect(Collectors.toList());
				break;
			} else {
				System.out.println("로그인 정보가 맞지 않습니다. 다시 입력하세요.");
			}
		}
		return userStream;
	}

	public static void printUser(ArrayList<User> userList) {
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
		}
	}

	public static void loadFile(ArrayList<User> userList) {
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

	public static String loginMenu(List<User> userStream) {
		User user = new User();
		System.out.println("메뉴를 선택하세요.");
		System.out
				.println("현재 프로필 : " + userStream.stream().map(s -> s.getProfileName1()).collect(Collectors.toList()));
		System.out.println("1. 프로필 선택 2. 계정 관리");
		System.out.println("3. 검색 4. 오늘의 TOP 5");
		System.out.println("5. 카테고리별 검색 6. 국가별 검색");
		System.out.println("7. 내가 찜한 목록 8. 다운로드 목록");
		System.out.println("9. 시청 목록 10. 고객센터  ");
		System.out.print("11. 로그아웃   ");
		String number = sc.nextLine();
		return number;
	}

}
