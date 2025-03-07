package CLI;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import FoodCourt.*;
import Sales.*;
import Output.*;
import Payment.Payment;


//CliFrame曖 л熱菟擎 餌辨濠陛 爾堅 殮溘ж朝 睡碟虜 氬渡м棲棻.
public class CliFrame {
	private static ArrayList<String> cli_list_first_col = new ArrayList<String>();
	private static ArrayList<String> cli_list_second_col = new ArrayList<String>();
	private static ArrayList<String> cli_list_third_col = new ArrayList<String>();
	
	/*-----------------------------------------------------
	 * 
	 * 奢鱔詭景 睡碟.
	 * 
	 *-------------------------------------------------------*/
	public static void clearConsole()
	{
		for(int i =0; i < 300; i++) System.out.println("");
	}
	
	
	public static void showStartMenuUi(FoodCourt foodcourt)
	{
		clearConsole();
		Scanner sc = new Scanner(System.in);
		boolean isExit = false;
		char input = 'a'; String str;
		
		
		while(isExit == false)
		{
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛                                  弛");
			System.out.println("弛               POFS               弛");
			System.out.println("弛                                  弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.println("摹鷗ж褒 詭景 廓�ㄧ� 殮溘п輿撮蹂.");
			System.out.println("1.輿僥籀葬\t2.詭景婦葬\t4.謙猿");
			str = sc.nextLine();
			if(!str.equals(""))input = str.charAt(0);
			clearConsole();
			
			switch(input)
			{
			case '1':
				showOrderProcessUi(foodcourt.getOrderbook());
				break;
			case '2':
				showMenuManagementUi(foodcourt);
				break;
			case '3':
				//棻擠縑 掘⑷腆 睡碟.
				break;
			case '4':
				isExit = true;
				sc.close();
				break;
			default:
				
				System.out.println("[螢夥腦雖 彊擎 殮溘殮棲棻.]");
				break;
			}
		}
	}
	
	
	/*----------------------------------------------------------------
	 * 
	 * 
	 * 輿僥 籀葬 だお.
	 * 
	 * 
	 * ------------------------------------------------------------*/
	
	public static void showOrderProcessUi(OrderBook orderbook)
	{	
		Scanner sc = new Scanner(System.in);
		boolean is_exit = false;
		int food_number = 0, food_price = 0;
		String food_name = ""; int input_price;
		char input = 'a'; boolean is_food_exists = true;
		int total = 0;
		String str;
		clearConsole();
		
		
		
		showOrderedFoodList(total);
		orderbook.makeNewSale();
		Sale cur_sale = orderbook.getSales().last();
		
		while(is_exit == false)
		{
			
			System.out.println("1.擠衝 殮溘\t2.唸薯ж晦\t3.謙猿");
			str = sc.nextLine();
			if(!str.equals(""))input = str.charAt(0);
		
			switch(input)
			{
			case '1':
				try
				{
					//堅偌檜 Cashier 縑啪 輿僥й 擠衝擊 蜓и棻.
					System.out.println("輿僥й 擠衝 檜葷擊 殮溘ж撮蹂.");
					food_name = sc.nextLine();
					System.out.println("輿僥й 擠衝曖 熱榆擊 殮溘ж撮蹂.");
					food_number = sc.nextInt(); sc.nextLine();
					
					if(is_food_exists && (food_number < 100) && (food_number > 0))
					{
						
						FoodDescription fd = orderbook.getFoodcourt().getFoodCatalogByFoodName(food_name).getFoodDescription(food_name);
						food_price = fd.getPrice();

						
						orderbook.enterFood(food_name, food_number);
						addOrderedFoodList(food_name, food_number, food_price*food_number);
						//CLI 葬蝶お縑 擠衝擊 蹺陛м棲棻. 欽牖�� 爾罹輿朝 葬蝶お縑 蹺陛ж朝 羲й殮棲棻.
						clearConsole();
						
						cur_sale.calculateTotal();
						total = cur_sale.getTotal();
						showOrderedFoodList(total);
						System.out.println("[擠衝檜 殮溘腎歷蝗棲棻.]");
						//輿僥и 擠衝擊 籀葬ж朝 瑞ず檜 菟橫陛撿 м棲棻. isFoodExists蒂 鱔п п渡 擠衝檜 襄營ж朝 擠衝檣雖
						//憲溥醜撿м棲棻. food_price縑 擠衝曖 陛問擊 憲溥醜撿 м棲棻.
						
					}
					else
					{
						clearConsole();
						showOrderedFoodList(total);
						System.out.println("[澀跤脹 殮溘殮棲棻.]");
					}
				}
				catch(InputMismatchException e)
				{
					sc.nextLine();
					clearConsole();
					showOrderedFoodList(total);
					System.out.println("[熱榆擎 薑熱煎 殮溘腎橫撿 м棲棻.]");
				}
				break;
			case '2':
				try
				{
					//Cashier陛 堅偌縑啪 識 陛問擊 蜓п輿堅 唸薯蒂 蹂羶и棻.
					
					cur_sale.calculateTotal();
					total = cur_sale.getTotal();
					System.out.printf("%d錳 唸薯ж衛啊蝗棲梱?\n1.蕨\t2.嬴棲螃\n", total);
					input = sc.nextLine().charAt(0);
				
					if(input != '1') 
					{
						clearConsole();
						showOrderedFoodList(total);
						System.out.println("[唸薯蒂 鏃模ж艘蝗棲棻.]");
						break;
					}
					//Cashier陛 嫡擎 旎擋擊 殮溘и棻.
					System.out.println("嫡擎 旎擋擊 殮溘п輿褊衛螃.");
					input_price = sc.nextInt(); sc.nextLine();
					
					
					orderbook.makeCashPayment(total, input_price);
					Payment cur_payment = cur_sale.getPayment();
					//衛蝶蠱檜 剪蝸楝還 旎擋擊 爾罹遽棻.
					System.out.printf("剪蝸楝還 旎擋擎 %d 殮棲棻. 啗樓ж溥賊 嬴鼠酈釭 殮溘ж褊衛螃.\n", cur_payment.getChange());
					sc.nextLine();
					
					//衛蝶蠱檜 諫猿脹 っ衙蒂 晦煙ж堅 衙轎縑 奩艙и 菴 輿寞戲煎 瞪殖п遽棻.
				
					
					orderbook.endSale();
					
					
					//衛蝶蠱檜 艙熱隸擊 轎溘и棻.
					System.out.printf("啗樓ж溥賊 嬴鼠酈釭 殮溘ж褊衛螃.\n");
					sc.nextLine();
					
		
					//衛蝶蠱檜 輿寞婁 啗骯渠縑 廓��ル蒂 轎溘и棻.
					//啗骯渠曖 艙熱隸婁 廓��ル朝 緒艇 籀葬蒂 嬪п憮 CLI陛 菟堅氈朝 葬蝶お蒂 滲熱煎 嫡堅 氈蝗棲棻.
					//Domain Layer 撲啗衛 評煎 勒萄萵 в蹂陛 橈蝗棲棻.
				
					//Cashier陛 っ衙 諫猿 籀葬蒂 и棻.
				
					clearConsole(); 
				
					cli_list_first_col = new ArrayList<String>();
					cli_list_second_col = new ArrayList<String>();
					cli_list_third_col = new ArrayList<String>();
				
					total = 0;
					showOrderedFoodList(total);
					System.out.println("[唸薯蒂 諫猿ж艘蝗棲棻.]");
					orderbook.makeNewSale();
					cur_sale = orderbook.getSales().last();
					
				}
				catch(InputMismatchException e)
				{
					clearConsole();
					showOrderedFoodList(total);
					System.out.println("[陛問擎 薑熱煎 殮溘腎橫撿 м棲棻.]");
				}
				break;
			case '3':
				clearConsole();
				
				cli_list_first_col = new ArrayList<String>();
				cli_list_second_col = new ArrayList<String>();
				cli_list_third_col = new ArrayList<String>();
				
				is_exit = true;
				break;
			default:
				clearConsole();
				showOrderedFoodList(total);
				System.out.println("[澀跤脹 殮溘殮棲棻.]");
			}
			
		}
	}
	
	private static void addOrderedFoodList(String food_name, int food_number, int food_price)
	{
		cli_list_first_col.add(food_name);
		cli_list_second_col.add(String.format("%d偃", food_number));
		cli_list_third_col.add(String.format("%d錳", food_price));
	}
	
	public static void showOrderedFoodList(int total)
	{
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛  擠衝貲 --- 擠衝 偃熱 --- 擠衝陛問.           弛");
		System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
		for(int i =0; i < cli_list_first_col.size(); i++)
		{
			System.out.printf("   %s --- %s --- %s\n", cli_list_first_col.get(i),
					cli_list_second_col.get(i), cli_list_third_col.get(i));
		}
		System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
		System.out.printf("                м啗: %d\n", total);
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
	}
	
	public static void printRecepit(int receipt_number, int total, SortedSet<SaleLineitem> lineitems) 
	{
		String time;
		SimpleDateFormat time_format = new SimpleDateFormat("yy喇 MM錯 dd橾 hh衛 mm碟 ss蟾");
		Calendar calender = Calendar.getInstance();
		time = time_format.format(calender.getTime());
		SaleLineitem sli;
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛  艙熱隸.                               弛");
		System.out.println("弛  擠衝貲 --- 擠衝 偃熱 --- 擠衝陛問.           弛");
		System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
		System.out.printf("   艙熱隸 廓��: %d\n", receipt_number);
		System.out.printf("   嫦晝 衛除   : %s\n", time);
		System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
		
		Iterator<SaleLineitem> it = lineitems.iterator();
		while(it.hasNext()) {
			sli = it.next();
			System.out.printf("   %s --- %s --- %s\n", sli.getfoodname(),
					sli.getquantity(), sli.getSubTotal());
		}
		
		System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
		System.out.printf("              旎擋 м啗: %d\n", total);
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		System.out.println("[艙熱隸檜 嫦晝腎歷蝗棲棻.]");
	}
	
	public static void printNumberTicket(int food_number, String food_name)
	{
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛  廓��ル.                    弛");
			System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
			System.out.printf("   牖廓: %d\n", food_number);
			System.out.printf("   擠衝: %s\n", food_name);
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
	}
	

	/*----------------------------------------------------------
	 * 
	 * 詭景 婦葬 だお
	 * 
	 * 
 	------------------------------------------------------------*/

	public static void showMenuManagementUi(FoodCourt foodcourt)
	{		
		char input = 'a';	boolean is_exit = false;
		Scanner sc = new Scanner(System.in);
		String food_name, kitchen_name; int food_price;
		boolean is_exists_kitchen_name = true;
		String str;
		
		
		initKitchenNFoodList(foodcourt);
		clearConsole();
		showKitchenNFoodList();
		
		
		foodcourt.startFoodModification();
		
		
		while(is_exit == false)
		{
			System.out.println("1.擠衝 蹺陛\t2.謙猿");
			str = sc.nextLine();
			if(!str.equals(""))input = str.charAt(0);
			
			
			switch(input)
			{
			case '1':
				try
				{
					//Manager陛 蹺陛й 擠衝曖 檜葷婁 陛問擊 殮溘и棻. Manager陛 蹺陛й 擠衝檜 樓ж朝 輿寞 檜葷擊 殮溘и棻.
					System.out.println("蹺陛й 擠衝曖 檜葷擊 殮溘ж撮蹂.");
					food_name = sc.nextLine();
					System.out.println("蹺陛й 擠衝曖 陛問擊 殮溘ж撮蹂.");
					food_price = sc.nextInt(); sc.nextLine();
					System.out.println("蹺陛й 擠衝檜 樓ж朝 輿寞擊 殮溘ж撮蹂.");
					kitchen_name = sc.nextLine();
					//衛蝶蠱檜 殮溘脹 薑爾蒂 鱔п憮 輿寞婁 擠衝曖 葬蝶お蒂 機等檜おи棻.
				
					if(is_exists_kitchen_name)
					{
						addKitchenNFoodList(kitchen_name, food_name, food_price);
						foodcourt.addFood(food_name, food_price, kitchen_name);
						//褒薯 殮溘脹 薑爾蒂 DCD縑 氈朝 瞳瞰и 偌羹縑 蹺陛ж朝 瑞ず檜 в蹂м棲棻.
				 
						//衛蝶蠱檜 Manager縑啪 機等檜お脹 輿寞婁 擠衝曖 葬蝶お蒂 爾罹遽棻.
						clearConsole();
						showKitchenNFoodList();
						System.out.println("[殮溘縑 撩奢ж艘蝗棲棻.]");
					}
					else
					{
						//嬴霜 堅溥ж雖 彊朝 睡碟殮棲棻.(櫛蝶蘸暮 衛釭葬螃)
					}
				}
				catch(InputMismatchException e)
				{
					sc.nextLine();
					clearConsole();
					showKitchenNFoodList();
					System.out.println("[陛問擎 薑熱煎 殮溘腎橫撿 м棲棻.]");
				}
				break;
			case '2':
				//褒薯 殮溘脹 薑爾蒂 盪濰ж朝 瑞ず檜 в蹂м棲棻.
				
				is_exit = true;
				cli_list_first_col = new ArrayList<String>();
				cli_list_second_col = new ArrayList<String>();
				cli_list_third_col = new ArrayList<String>();
				foodcourt.endFoodModification();
				clearConsole();
				break;
			default:
				clearConsole();
				showKitchenNFoodList();
				System.out.println("[澀跤 殮溘腎歷蝗棲棻.]");
				break;
			}
		}
	}
	
	public static void showKitchenNFoodList()
	{
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛  輿寞貲 --- 擠衝 檜葷 --- 擠衝陛問.           弛");
		System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
		for(int i =0; i < cli_list_first_col.size(); i++)
		{
			System.out.printf("   %s --- %s --- %s\n", cli_list_first_col.get(i),
					cli_list_second_col.get(i), cli_list_third_col.get(i));
		}
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
	}
	
	private static void addKitchenNFoodList(String kitchen_name, String food_name, int food_price)
	{
		cli_list_first_col.add(kitchen_name);
		cli_list_second_col.add(food_name);
		cli_list_third_col.add(String.format("%s", food_price));
	}
	
	public static void initKitchenNFoodList(FoodCourt foodcourt)
	{
		SortedSet<FoodCatalog> fc = foodcourt.getAllFoodCatalog();
		String food_name, kitchen_name; int food_price;
		
		Iterator<FoodCatalog> cit = fc.iterator();
		Iterator<FoodDescription> dit;
		FoodCatalog cur = null; FoodDescription fd = null;
		int max_food_number = foodcourt.getMaxFoodNumber();
		int max_kitchen_number = foodcourt.getMaxKitchenNumber();
		int index = 0;
		int inner_index = 0;
		
		SortedSet<FoodDescription> fooddescriptions = new TreeSet<FoodDescription>();
		
		while(cit.hasNext() && index < max_kitchen_number) {
			cur = cit.next();
			kitchen_name = cur.getKitchen_name();
			fooddescriptions = cur.getAllFooddescriptions();
			dit = fooddescriptions.iterator();
			while(dit.hasNext() && inner_index < max_food_number) {
				fd = dit.next();
				food_name = fd.getFood_name();
				food_price = fd.getPrice();
				addKitchenNFoodList(kitchen_name, food_name, food_price);
				inner_index++;
			}
			
			index++;
		}
	}
	
}









