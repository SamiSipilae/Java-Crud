import java.util.*;
import java.io.*;

public class projekti
{

	public static void main(String[] args)
	{

		List<human> humans = new ArrayList<human>();
		while (true)
		{
			int choice = print_menu();
			switch (choice)
			{
			case 0:
				return;
			case 1:
				print_data(humans);
				break;
			case 2:
				add_data(humans);
				break;
			case 3:
				remove_data(humans);
				break;
			case 4:
				save_data(humans);
				break;
			case 5:
				load_data(humans);
				break;
			}

		}
	}

	public static int print_menu()
	{

		System.out.println("1. Print data");
		System.out.println("2. Add data");
		System.out.println("3. Remove data");
		System.out.println("4. Save data");
		System.out.println("5. Load data");
		System.out.println("0. Quit");

		while (true)
		{

			int n = get_int();
			if (n >= 0 && n <= 5)
			{

				return n;
			}

		}
	}

	public static void print_data(List<human> humans)
	{
		for (human person : humans)
		{
			System.out.println(person);
		}
		return;
	}

	public static void add_data(List<human> humans)
	{

		System.out.println("Name: ");
		String name = get_string();
		System.out.println("Age: ");
		String age = get_string();
		System.out.println("Model of car, leave empty if no car:");
		String model = get_string();
		model = model.replace(System.getProperty("line.separator"), "");
		if (!model.isEmpty())
		{
			System.out.println("Brand of car: ");
			String brand = get_string();
			System.out.println("Number plate of car: ");
			String plate = get_string();
			System.out.println("Manufacturing year of car: ");
			String year = get_string();
			car owned_car = new car(brand, model, plate, year);
			human new_human = new human(name, age, owned_car);
			humans.add(new_human);

		} else
		{
			human new_human = new human(name, age, null);
			humans.add(new_human);
		}

		return;
	}

	public static void remove_data(List<human> humans)
	{
		int num = 1;
		for (human person : humans)
		{
			System.out.println(Integer.toString(num) + " " + person);
			num++;
		}
		System.out.println(
				"Enter number of record to be deleted, empty to cancel:");
		int del = get_int();
		humans.remove(del - 1);
		return;
	}

	public static void save_data(List<human> humans)
	{
		// technically we are breaking CSV spec, since we use varying number of columns
		// but I don't  think it matters in this context
		System.out.println("Enter filename: ");
		String filename = get_string();
		FileWriter fileWriter = null;

		try
		{
			fileWriter = new FileWriter(filename);
			for (human person : humans)
			{
				fileWriter.append(person.toCSV());
				fileWriter.append(System.getProperty("line.separator"));
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return;
	}

	public static void load_data(List<human> humans)
	{
		System.out.println("Enter filename: ");
		String filename = get_string();
		BufferedReader fileReader = null;
		String line = "";

		try
		{
			fileReader = new BufferedReader(new FileReader(filename));
			while ((line = fileReader.readLine()) != null)
			{
				String[] tokens = line.split(",");
				if (tokens.length > 0)
				{
					if (tokens.length > 2)
					{
						car owned_car = new car(tokens[2], tokens[3], tokens[4],
								tokens[5]);
						human new_human = new human(tokens[0], tokens[1],
								owned_car);
						humans.add(new_human);
					} else
					{
						human new_human = new human(tokens[0], tokens[1], null);
						humans.add(new_human);
					}
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				fileReader.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return;
	}

	@SuppressWarnings("resource")
	public static String get_string()
	{
		while (true)
		{

			String input;
			Scanner reader = new Scanner(System.in);
			try
			{

				input = reader.nextLine();
				return input;

			}

			catch (InputMismatchException e)
			{
				reader.nextLine();

			}

		}
	}

	@SuppressWarnings("resource")
	public static int get_int()
	{
		Scanner reader = new Scanner(System.in);
		while (true)
		{

			int input;

			try
			{

				input = reader.nextInt();
				return input;

			}

			catch (InputMismatchException e)
			{

				System.out.println("Invalid input");
				reader.nextLine();

			}

		}
	}
}
