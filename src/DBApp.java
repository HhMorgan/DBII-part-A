
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;

public class DBApp {

	public static void main(String[] args) throws DBAppException, IOException {
		/*
		 * Hashtable<String, String> ht = new Hashtable<>(); ht.put("id",
		 * "java.lang.Integer"); ht.put("age", "java.lang.Integer");
		 * createTable("employee", ht, "id"); createTable("empss", ht, "id");
		 * System.out.println(Arrays.deepToString(readCSV("metadata.csv",
		 * "empss").toArray()));
		 */

		/*
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
		 * "yyyy/MM/dd HH:mm:ss"); LocalDateTime now = LocalDateTime.now();
		 * System.out.println(now); System.out.println(dtf.format(now));
		 */
		/*
		 * String timeStamp = new
		 * SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		 * System.out.println(timeStamp.getClass());
		 */

		/*
		 * Date date=new Date(); System.out.println(date);
		 */

		// test cases

		// String strTableName = "Student";
		// Hashtable htblColNameType = new Hashtable( );
		// htblColNameType.put("id", "java.lang.Integer");
		// htblColNameType.put("name", "java.lang.String");
		// htblColNameType.put("gpa", "java.lang.double");
		// createTable( strTableName, "id", htblColNameType );
		// createBRINIndex(strTableName, "gpa" );

		// tupels
		// Init loop :)
		// for(int i=0;i<201;i++){
		// Hashtable htblColNameValue = new Hashtable();
		// // change the number here with i and don't forget to remove all of
		// the
		// // pages except the first.
		// // Also dont forget to delete the content of the first page. HAVE FUN
		// =D
		// htblColNameValue.put("id", new Integer(i));
		// htblColNameValue.put("name", new String("Ahmed Nkoor"));
		// htblColNameValue.put("gpa", new Double(0.95));
		// // htblColNameValue.put("lol", new String("lol"));
		// insertIntoTable("Student", htblColNameValue);
		// }
		// deleteFromTable("Student", htblColNameValue);
		// 0updateTable("Student", "name", htblColNameValue);
		// ------------------------------
		// test v0.02
		// ignoring trivial test cases (tested before)
		// ~
		// bugs so far:
		// handling empty tables
		// handling creating a table with an invalid clmn(*)
		// ~
		// working:
		// creating a table
		// handling invalid types (*)
		// insertion
		// handling invalid name , type
		// t2 updating
		// t4 invalid name
		// t5 update primary key
		// t3 wrong primary
		// deleting by primary key
		// test cases:
		// t1 creating using a diffrent hash table
		String strTableName = "Table1";
		// t0 Hashtable htblColNameType = new Hashtable( );
		// t0 createTable( "Table2", "id", htblColNameType );
		// t1 Hashtable htblColNameType = new Hashtable( );
		// t1 htblColNameType.put("id", "java.lang.Integer");
		// t1 htblColNameType.put("name", "java.lang.String");
		// t1 htblColNameType.put("gpa", "java.lang.double");
		// t1 createTable( "Table1", "id", htblColNameType );
		// t1 Hashtable htblColNameValue = new Hashtable( );
		// t1 htblColNameValue.put("id", new Integer( 2343433 ));
		// t1 htblColNameValue.put("name",new String("Ahmed Noor"));
		// t1 htblColNameValue.put("gpa",new Double(0.95));
		// t1 insertIntoTable("Table1",htblColNameValue);
		// t2 updating
		// t2 Hashtable x = new Hashtable( );
		// t2 x.put("id", new Integer( 2343432 ));
		// t2 x.put("name",new String("Ahmed Noor"));
		// t2 x.put("gpa",new Double(0.97));
		// t2 updateTable("Table1","id",x);
		// t3 wrong primary
		// t3 x.put("id", new Integer( 2343432 ));
		// t3 x.put("name",new String("Ahmed Noor"));
		// t3 x.put("gpa",new Double(0.97));
		// t3 updateTable("Table1","name",x);
		// t4 invalid name
		// t4 Hashtable x = new Hashtable( );
		// t4 x.put("id", new Integer( 2343432 ));
		// t4 x.put("name",new String("Ahmed Noor"));
		// t4 x.put("xd",new Double(0.97));
		// t4 updateTable("Table1","id",x);
		// t5 update primary key
		// t6 x.put("id", new Integer( 2343436 ));
		// t6 x.put("name",new String("Ahmed Noor"));
		// t6 x.put("gpa",new Double(0.97));
		// t6 updateTable("Table1","id",x);
		// t7 deleting
		// t7Hashtable d = new Hashtable( );
		// t7d.put("id", new Integer(2343432));
		// t7d.put("name",new String("Ahmed Noor"));
		// t7d.put("gpa",new Double(0.97));
		// t7deleteFromTable("Table1",d);
		// =====================================================================================
		// String strTableName = "Table1";
		Tuple tuple = new Tuple();
		PriorityQueue<Tuple> table = new PriorityQueue<Tuple>();
		ArrayList<PriorityQueue<Tuple>> tableOfTables = new ArrayList<PriorityQueue<Tuple>>();
		ArrayList<String> metadataArray = readCSV("metadata.csv", strTableName);
		// The value of N that should be changed based on the config file
		int n = 200;
		try {
			String path = strTableName + File.separator + "Page" + 1 + ".class";
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = null;
			if (fileIn.available() != 0) {
				for (int i = 2;; i++) {

					if (fileIn.available() != 0) {
						in = new ObjectInputStream(fileIn);
						PriorityQueue<Tuple> tableTmp = (PriorityQueue<Tuple>) in.readObject();
						tableOfTables.add(tableTmp);

						// fileIn.close();

					}
					path = strTableName + File.separator + "Page" + i + ".class";
					// System.out.println(path);
					File f = new File(path);
					if (f.isFile() && f.canRead()) {
						// System.out.println("hi");
						fileIn = new FileInputStream(path);
					} else
						break;
					// printTuples(table);
				}
				in.close();
				fileIn.close();
				System.out.println("tableOfTables size : " + tableOfTables.size());
				for (int i = 0; i < tableOfTables.size(); i++) {
					int queueInitialSize = tableOfTables.get(i).size();
					for (int j = 0; j < queueInitialSize; j++) {
						table.add(tableOfTables.get(i).remove());

					}
				}

				// in.close();
				// printTuples(table);
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class is non existant");
			c.printStackTrace();
			return;
		}
		// =====================================================================================
		printTuples(table);

		// ------------------------------

		// }
		// end of it
		//
		// System.out.print(readCSV("metadata.csv","Student"));
		/*
		 * String tmp="Hello World!";
		 * 
		 * try { FileOutputStream fileOut =new FileOutputStream("tmp.class");
		 * ObjectOutputStream out = new ObjectOutputStream(fileOut);
		 * out.writeObject(tmp); out.close(); fileOut.close();
		 * System.out.println("Serialized data"); } catch (IOException i) {
		 * i.printStackTrace(); } String out; try { FileInputStream fileIn = new
		 * FileInputStream("tmp.class"); ObjectInputStream in = new
		 * ObjectInputStream(fileIn); out = (String) in.readObject();
		 * in.close(); fileIn.close(); } catch (IOException i) {
		 * i.printStackTrace(); return; } catch (ClassNotFoundException c) {
		 * System.out.println("Employee class not found"); c.printStackTrace();
		 * return; } System.out.println(out);
		 */
		/*
		 * String strTableName = "Student"; Hashtable htblColNameType = new
		 * Hashtable( ); htblColNameType.put("id", "java.lang.Integer");
		 * htblColNameType.put("name", "java.lang.String");
		 * htblColNameType.put("gpa", "java.lang.double"); createTable(
		 * strTableName, "id", htblColNameType );
		 */

		/* */

	}

	public static void emptyHash(Hashtable<String, String> columnsnametype) throws DBAppException {
		// raises exception if the input hashtable was empty
		if (columnsnametype.isEmpty())
			throw new DBAppException("You are trying to use an empty hashtable");
	}

	public static void emptyHashObject(Hashtable<String, Object> columnsnametype) throws DBAppException {
		// raises exception if the input hashtable was empty
		if (columnsnametype.isEmpty())
			throw new DBAppException("You are trying to use an empty hashtable");
	}

	public static void createTable(String tablename, String key, Hashtable<String, String> columnsnametype)
			throws DBAppException, IOException {// Creates the table folder and
		// initial meta data in our
		// storage
		emptyHash(columnsnametype);// checks if the input is empty or not
		if (!columnsnametype.containsKey(key))// checks if the primary key was
			// in the input fields
			throw new DBAppException("The key has to be part of the table");
		// inputHashtablecontent holds the content of the input hashtable
		ArrayList<String> inputHashtablecontent = new ArrayList<String>(columnsnametype.values());
		for (int i = 0; i < inputHashtablecontent.size(); i++) {
			// inputHashtablecontentString holds the ith element in the input
			// hashtable
			String inputHashtablecontentString = inputHashtablecontent.get(i);
			inputHashtablecontentString = inputHashtablecontentString.toLowerCase();
			// checks if the type of the input were in the correct format or not
			if (inputHashtablecontentString.equalsIgnoreCase("java.lang.Integer")
					|| inputHashtablecontentString.equalsIgnoreCase("java.lang.String")
					|| inputHashtablecontentString.equalsIgnoreCase("java.lang.Double")
					|| inputHashtablecontentString.equalsIgnoreCase("java.lang.Boolean")
					|| inputHashtablecontentString.equalsIgnoreCase("java.util.Date")) {
				continue;
			} else {
				throw new DBAppException("Invalid Data Type.");
			}
		}
		try {// creates metadata in our metadata storage
			loadCSV(columnsnametype, tablename, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// dir is the directory of the new folder that will be created for the
		// new table
		File dir = new File(tablename);
		dir.mkdir();
		String firstPagedir = tablename + File.separator + "Page1.class";
		File f = new File(firstPagedir);
		f.getParentFile().mkdirs();
		f.createNewFile();
	}

	public static void loadCSV(Hashtable<String, String> columnsnametype, String tablename, String pk)
			throws IOException {
		// loads the CSV file and writes the table's metadata in it
		// pw is a PrintWriter that appends the new data to the end of the file
		// instead of overwriting it
		PrintWriter pw = new PrintWriter(new FileWriter("metadata.csv", true));
		StringBuilder sb = new StringBuilder();// sb is the new data that will
		// be appended to our metadata
		// storage
		// appends the column defining row
		sb.append("Table Name,Column Name,Column Type,Key,Indexed\n");
		Enumeration<String> enumKey = columnsnametype.keys();
		// loops over the columns and enter each one of them in the storage
		while (enumKey.hasMoreElements()) {
			String key = enumKey.nextElement();// name of table
			String val = columnsnametype.get(key);// type
			sb.append(tablename + "," + key + "," + val + "," + key.equals(pk) + "," + false + '\n');

		}
		// appends the type of the not know to user column that holds the date
		// of the last update
		sb.append(tablename + "," + "TouchDate" + "," + "java.util.Date" + "," + false + "," + false + '\n');
		pw.write(sb.toString());
		pw.close();
		// a confirmation message for debugging purposes
		System.out.println("done!");
	}

	public static PriorityQueue<Tuple> Readfiles(String strTableName) throws IOException, DBAppException {
		// Reads the stored files of the table that was selected by the user
		// Tuple is data structure that we have created that is an arraylist
		// that implements comparable so we can compare the primary key easily
		// Because Tuple implements comparable, we have decided to use priority
		// queues to have the data automatically sorted
		// table holds all of the data that we have in the stored files in a 2D
		// form
		PriorityQueue<Tuple> table = new PriorityQueue<Tuple>();
		// tableOfTables is a 3D data structure that holds the pieces of the
		// data that we have stored in the database with each index holding one
		// page
		// for example if we have 3 pages and each page has 200 rows, then
		// tableOfTables' size will be 3 with each index holding a 2D array that
		// holds one page
		// Nb. we consider the PriorityQueue as a normal array in this
		// documentation to ease the explanation
		ArrayList<PriorityQueue<Tuple>> tableOfTables = new ArrayList<PriorityQueue<Tuple>>();
		try {
			// path is the directory of the stored pages, we start with 1 as its
			// the first entry in our storage system
			String path = strTableName + File.separator + "Page" + 1 + ".class";
			// fileIn reads the file from the path
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = null;
			// the condition checks if the file exists or is that table non
			// existent in the first place
			if (fileIn.available() != 0) {
				// we start the loop with i=2 because we will fetch the first
				// file first then we start looping
				// this point will be explained better inside the loop
				for (int i = 2;; i++) {
					// the condition checks if the page exists
					if (fileIn.available() != 0) {
						// we load the content of the page
						in = new ObjectInputStream(fileIn);
						// we use a tmp 2D array that we will store in
						// tableOfTables
						PriorityQueue<Tuple> tableTmp = (PriorityQueue<Tuple>) in.readObject();
						tableOfTables.add(tableTmp);
					}
					// we initialize the directory for the next page
					path = strTableName + File.separator + "Page" + i + ".class";
					File f = new File(path);
					// checks if the page exists or not
					if (f.isFile() && f.canRead()) {
						// loads the next page
						fileIn = new FileInputStream(path);
					} else // the pages have ended, so we break out of the
						// reading process
						break;
					// printTuples(table);
				}
				in.close();
				fileIn.close();
				// This line is for debugging purposes only
				System.out.println("tableOfTables size : " + tableOfTables.size());
				// loads the content of the 3D array (tableOfTables) into the 2D
				// array (table) to ease the process of handling arrays
				// as manipulating a 2D array is easier than 3D array and will
				// also decrease the time & space complexity of the program
				for (int i = 0; i < tableOfTables.size(); i++) {
					int queueInitialSize = tableOfTables.get(i).size();
					for (int j = 0; j < queueInitialSize; j++) {
						table.add(tableOfTables.get(i).remove());

					}
				}
				// This line is for debugging purposes only
				printTuples(table);
				// returns the table to be used in the main program
				return table;
			}
		} catch (IOException i) {
			throw new DBAppException("Database is non existant");
		} catch (ClassNotFoundException c) {
			throw new DBAppException("Class is non existant");
		}
		return table;
	}

	//============================================================================================
	public static ArrayList<PriorityQueue> ReadFilesBrin(String strTableName) throws IOException, DBAppException {
		ArrayList<PriorityQueue> tableOfTables = new ArrayList<PriorityQueue>();
		try {
			// path is the directory of the stored pages, we start with 1 as its
			// the first entry in our storage system
			String path = strTableName + File.separator + "Page" + 1 + ".class";
			// fileIn reads the file from the path
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = null;
			// the condition checks if the file exists or is that table non
			// existent in the first place
			if (fileIn.available() != 0) {
				// we start the loop with i=2 because we will fetch the first
				// file first then we start looping
				// this point will be explained better inside the loop
				for (int i = 2;; i++) {
					// the condition checks if the page exists
					if (fileIn.available() != 0) {
						// we load the content of the page
						in = new ObjectInputStream(fileIn);
						// we use a tmp 2D array that we will store in
						// tableOfTables
						PriorityQueue<ArrayList> tableTmp = (PriorityQueue<ArrayList>) in.readObject();
						tableOfTables.add(tableTmp);
					}
					// we initialize the directory for the next page
					path = strTableName + File.separator + "Page" + i + ".class";
					File f = new File(path);
					// checks if the page exists or not
					if (f.isFile() && f.canRead()) {
						// loads the next page
						fileIn = new FileInputStream(path);
					} else // the pages have ended, so we break out of the
						// reading process
						break;
					// printTuples(table);
				}
				in.close();
				fileIn.close();
				// This line is for debugging purposes only
				System.out.println("tableOfTables size : " + tableOfTables.size());
				// loads the content of the 3D array (tableOfTables) into the 2D
				// array (table) to ease the process of handling arrays
				// as manipulating a 2D array is easier than 3D array and will
				// also decrease the time & space complexity of the program

				// This line is for debugging purposes only
				//printTuples(table);
				// returns the table to be used in the main program
				return tableOfTables;
			}
		} catch (IOException i) {
			throw new DBAppException("Database is non existant");
		} catch (ClassNotFoundException c) {
			throw new DBAppException("Class is non existant");
		}
		return tableOfTables;
	}

	//============================================================================================


	public static void WriteFile(String strTableName, PriorityQueue<Tuple> table, int n) {
		// The value of N that should be changed based on the config file
		try {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;
			// This line is for debugging purposes only
			System.out.println("table size : " + table.size());
			// tableInitialSizeModN is the number of pages that the table should
			// have
			int tableInitialSizeModN = table.size() / n;
			for (int i = 0; i <= tableInitialSizeModN; i++) {
				// initializes the directory of the page
				String path = strTableName + File.separator + "Page" + (i + 1) + ".class";
				fileOut = new FileOutputStream(new File(path));
				out = new ObjectOutputStream(fileOut);
				// tableTmp is the valuable that holds the part of the data that
				// should be stored next
				// for example, if i=3, n=200, and tableInitialSizeModN=4 then
				// tabletmp should hold
				// the data from index 399 to index 599
				PriorityQueue<Tuple> tableTmp = new PriorityQueue<Tuple>();
				for (int k = 0; ((k % n != 0 || k == 0) && !(table.isEmpty())); k++) {
					// we add the data from table to tableTmp so we can store it
					// later in our storage system
					tableTmp.add(table.remove());
				}
				// the file writing functionality
				out.writeObject(tableTmp);
			}
			out.close();
			fileOut.close();
			// This line is for debugging purposes only
			System.out.println("Job Done");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static ArrayList loadHashtableInput(Hashtable<String, Object> htblColNameValue,
			ArrayList<String> metadataArray) throws DBAppException {
		// tmpInteger stores the position of the primary key
		int tmpInteger = -1;
		// value stores the data of the key for a new row
		Object value = null;
		// tuple is the new arraylist that will hold the value of the row that
		// needs to be insearted
		Tuple tuple = new Tuple();
		// trioTuple is a tuple that holds the results of
		// (tuple,tmpInteger,value)
		// we have used this way so we can return all of the three value to the
		// main program
		ArrayList trioTuple = new ArrayList();
		// enumkey holds the values and column names of the input row
		Enumeration<String> enumKey = htblColNameValue.keys();
		while (enumKey.hasMoreElements()) {
			String colName = enumKey.nextElement();// name of table
			Object val = htblColNameValue.get(colName);// value of the field
			String valString = val.getClass().toString();// gets the type of the
			// field
			String[] typeArray = valString.split(" ");
			// flag checks if the column name is valid or not
			boolean flag = false;
			for (int i = 0; i < metadataArray.size(); i++) {
				// metaArray holds the type of each column from the metadata
				// file that we have in our storage
				String[] metaArray = metadataArray.get(i).split(",");
				// the conditions makes sure that the type of the field matches
				// the one in the metadata
				if (metaArray[1].equals(colName)) {
					flag = true;
					if (typeArray[1].equalsIgnoreCase(metaArray[2])) {
						tuple.add(val);
						// the condition checks if we have reached the key
						if (metaArray[3].equalsIgnoreCase("true")) {
							// tmpInteger stores the index of the key
							tmpInteger = i;
							// stores the value of the key
							value = val;
						}
						break;
					} else {
						throw new DBAppException("Invalid type");
					}
				}

			}
			if (!flag) {
				throw new DBAppException("Invalid column name");
			}

		}
		// adding the resulting output to trioTuple to return to the program
		trioTuple.add(tuple);
		trioTuple.add(tmpInteger);
		trioTuple.add(value);
		return trioTuple;
	}

	public static void insertIntoTable(String strTableName, Hashtable<String, Object> htblColNameValue)
			throws DBAppException, IOException {
		// inserts a row into the table
		emptyHashObject(htblColNameValue);
		PriorityQueue<Tuple> table = Readfiles(strTableName);
		ArrayList<String> metadataArray = readCSV("metadata.csv", strTableName);
		ArrayList trioTuple = loadHashtableInput(htblColNameValue, metadataArray);
		Tuple tuple = (Tuple) trioTuple.get(0);
		int tmpInteger = (int) trioTuple.get(1);
		Object value = (Object) trioTuple.get(2);
		tuple.add(new Date());
		for (Tuple t : table) {
			if (t.tupleData.get(tmpInteger).equals(value))
				throw new DBAppException("Primary key already exists");
		}
		tuple.setKey(value); // after checking that its a new key I'm setting,
		// its value in tuple
		table.add(tuple);
		WriteFile(strTableName, table, 200);
	}

	public static void deleteFromTable(String strTableName, Hashtable<String, Object> htblColNameValue)
			throws DBAppException, IOException {
		emptyHashObject(htblColNameValue);
		PriorityQueue<Tuple> table = Readfiles(strTableName);
		ArrayList<String> metadataArray = readCSV("metadata.csv", strTableName);
		ArrayList trioTuple = loadHashtableInput(htblColNameValue, metadataArray);
		Tuple tuple = (Tuple) trioTuple.get(0);
		int tmpInteger = (int) trioTuple.get(1);
		Object value = (Object) trioTuple.get(2);
		// ------------------------------------------------------------------------
		PriorityQueue<Tuple> tableLoopTmpWhyNot = new PriorityQueue<Tuple>();
		boolean flag = false;
		while (!table.isEmpty()) {
			Tuple tmpTuple = table.poll();
			Object pKey = tmpTuple.key;
			Object pkeyTmpTuple = tuple.tupleData.get(tmpInteger);
			if (pKey.equals(pkeyTmpTuple)) {
				flag = true;
				// table.remove(tuple);
				break;
			} else {
				tableLoopTmpWhyNot.add(tmpTuple);
				// throw new DBAppException("The row doesn't exist");
			}
			// tuple.remove(((Tuple) tmpTuple).tupleData.size() - 1);

		}
		if (!flag) {
			throw new DBAppException("The row doesn't exist");
		}
		while (!tableLoopTmpWhyNot.isEmpty()) {
			table.add(tableLoopTmpWhyNot.poll());
		}
		// =========================================================================
		WriteFile(strTableName, table, 200);

	}

	public static void updateTable(String strTableName, String strKey, Hashtable<String, Object> htblColNameValue)
			throws DBAppException, IOException {
		emptyHashObject(htblColNameValue);
		PriorityQueue<Tuple> table = Readfiles(strTableName);
		ArrayList<String> metadataArray = readCSV("metadata.csv", strTableName);
		ArrayList trioTuple = loadHashtableInput(htblColNameValue, metadataArray);
		Tuple tuple = (Tuple) trioTuple.get(0);
		int tmpInteger = (int) trioTuple.get(1);
		Object value = (Object) trioTuple.get(2);
		// ------------------------------------------------------------------------
		PriorityQueue<Tuple> tableLoopTmpWhyNot = new PriorityQueue<Tuple>();
		boolean flag = false;
		while (!table.isEmpty()) {
			Tuple tmpTuple = table.poll();
			Object pKey = tmpTuple.key;
			Object pkeyTmpTuple = tuple.tupleData.get(tmpInteger);
			// System.out.println(pkeyTmpTuple);
			if (pKey.equals(pkeyTmpTuple)) {
				flag = true;
				tuple.add(new Date());
				tuple.setKey(tuple.tupleData.get(tmpInteger));
				tableLoopTmpWhyNot.add(tuple);
				break;
			} else {
				tableLoopTmpWhyNot.add(tmpTuple);
				// throw new DBAppException("The row doesn't exist");
			}
			// tuple.remove(((Tuple) tmpTuple).tupleData.size() - 1);

		}
		if (!flag) {
			throw new DBAppException("The row doesn't exist");
		}
		while (!tableLoopTmpWhyNot.isEmpty()) {
			table.add(tableLoopTmpWhyNot.poll());
		}
		WriteFile(strTableName, table, 200);
	}

	public static void printTuples(PriorityQueue<Tuple> tupleData) {

		Iterator it = tupleData.iterator();
		while (it.hasNext()) {
			System.out.println(Arrays.toString(((Tuple) (it.next())).tupleData.toArray()));
		}
	}

	public static ArrayList<String> readCSV(String fileName, String tableName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<String> lines = new ArrayList<String>();
		String newLine;
		while ((newLine = br.readLine()) != null) {
			// newLine = br.readLine();
			String[] tmpArray = newLine.split(",");
			if (tmpArray[0].equals(tableName))
				lines.add(newLine);
		}
		br.close();
		return lines;
	}

	public static void createBRINIndex(String strTableName, String strColName) throws DBAppException, IOException {
		ArrayList<String> metadataArray = readCSV("metadata.csv", strTableName);
		boolean tableFound = false;
		boolean colFound = false;
		ArrayList<ArrayList<Object>> allBrinoftable = new ArrayList<>();
		for (int i = 0; i < metadataArray.size(); i++) {

			String[] metaArray = metadataArray.get(i).split(",");

			if (metaArray[0].equals(strTableName)) {
				tableFound = true;

				if (metaArray[1].equals(strColName)) {
					colFound = true;

					if (metaArray[2].equals("TRUE") && metaArray[3].equals("FALSE")) {
						// Set Index to True in Metadata
						ArrayList<PriorityQueue> table = ReadFilesBrin(strTableName);
						for(int j=0;j<table.size();j++){
							PriorityQueue<Tuple> q = table.get(i);
							Object[] qtoArray =	q.toArray();
							allBrinoftable.get(i).add(q.peek().key); //min
							allBrinoftable.get(i).add(qtoArray[q.size()-1]);//max
							allBrinoftable.get(i).add(i);//pointer equivalent to page number
						}

					}

					else if (metaArray[2].equals("FALSE") && metaArray[3].equals("FALSE")) {
						// After Midterms.
					}
				}
			}

		}

		if (!tableFound) {
			throw new DBAppException("Table Not Found.");
		}

		if (!colFound) {
			throw new DBAppException("Column Not Found.");
		}

	}
}
