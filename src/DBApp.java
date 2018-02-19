
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
		 * DateTimeFormatter dtf =
		 * DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); LocalDateTime now
		 * = LocalDateTime.now(); System.out.println(now);
		 * System.out.println(dtf.format(now));
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
		/*
		 * tabels String strTableName = "Student"; Hashtable htblColNameType =
		 * new Hashtable( ); htblColNameType.put("id", "java.lang.Integer");
		 * htblColNameType.put("name", "java.lang.String");
		 * htblColNameType.put("gpa", "java.lang.double"); createTable(
		 * strTableName, "id", htblColNameType ); //createBRINIndex(
		 * strTableName, "gpa" );
		 */
		// tupels
		// Init loop :)
		// for(int i=0;i<30;i++){
		//0Hashtable htblColNameValue = new Hashtable();
		// change the number here with i and don't forget to remove all of the
		// pages except the first.
		// Also dont forget to delete the content of the first page. HAVE FUN =D
		//0htblColNameValue.put("id", new Integer(12));
		//0htblColNameValue.put("name", new String("Ahmed Nkoor"));
		//0htblColNameValue.put("gpa", new Double(0.95));
		// htblColNameValue.put("lol", new String("lol"));
		// insertIntoTable("Student", htblColNameValue);
		// deleteFromTable("Student", htblColNameValue);
		//0updateTable("Student", "name", htblColNameValue);
		//------------------------------
		//test v0.02
		//ignoring trivial test cases (tested before)
		//~
		//bugs so far:
		//handling empty tables
		//handling creating a table with an invalid clmn(*)
		//~
		//working:
		//creating a table 
		//handling invalid types (*)
		//insertion
		//handling invalid name , type
		//t2 updating 
		//t4 invalid name
		//t5 update primary key
		//t3 wrong primary
		//deleting by primary key
		//test cases:
		//t1 creating using a diffrent hash table
		//String strTableName = "Student";
		//t1 Hashtable htblColNameType = new Hashtable( );
		//t1 htblColNameType.put("id", "java.lang.Integer");
		//t1 htblColNameType.put("name", "java.lang.String");
		//t1 htblColNameType.put("gpa", "java.lang.double");
		//t1 createTable( "Table1", "id", htblColNameType );
	     //t1 Hashtable htblColNameValue = new Hashtable( );
		//t1 htblColNameValue.put("id", new Integer( 2343433 ));
		//t1 htblColNameValue.put("name",new String("Ahmed Noor"));
		//t1 htblColNameValue.put("gpa",new Double(0.95));
		//t1 insertIntoTable("Table1",htblColNameValue);
		//t2 updating 
		//t2 Hashtable x = new Hashtable( );
		//t2 x.put("id", new Integer( 2343432 ));
		//t2 x.put("name",new String("Ahmed Noor"));
		//t2 x.put("gpa",new Double(0.97));
		//t2 updateTable("Table1","id",x);
		//t3 wrong primary
		//t3 x.put("id", new Integer( 2343432 ));
		//t3 x.put("name",new String("Ahmed Noor"));
		//t3 x.put("gpa",new Double(0.97));
		//t3 updateTable("Table1","name",x);
		//t4 invalid name
		//t4 Hashtable x = new Hashtable( );
		//t4 x.put("id", new Integer( 2343432 ));
		//t4 x.put("name",new String("Ahmed Noor"));
		//t4 x.put("xd",new Double(0.97));
		//t4 updateTable("Table1","id",x);
		//t5 update primary key
		//t6 x.put("id", new Integer( 2343436 ));
		//t6 x.put("name",new String("Ahmed Noor"));
		//t6 x.put("gpa",new Double(0.97));
		//t6 updateTable("Table1","id",x);
		//t7 deleting
		//t7Hashtable d = new Hashtable( );
		//t7d.put("id", new Integer(2343432));
		//t7d.put("name",new String("Ahmed Noor"));
		//t7d.put("gpa",new Double(0.97));
		//t7deleteFromTable("Table1",d);
		//=====================================================================================
		String strTableName = "Table1";
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
				//printTuples(table);
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class is non existant");
			c.printStackTrace();
			return;
		}
		//=====================================================================================
		printTuples(table);
		
		
		
		//------------------------------
		
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

	}

	public static void createTable(String tablename, String key, Hashtable<String, String> columnsnametype)
			throws DBAppException, IOException {
		if (!columnsnametype.containsKey(key))
			throw new DBAppException("The key has to be part of the table");
		ArrayList<String> a = new ArrayList<String>(columnsnametype.values());
		for (int i = 0; i < a.size(); i++) {
			String s = a.get(i);
			s = s.toLowerCase();
			if (s.equalsIgnoreCase("java.lang.Integer") || s.equalsIgnoreCase("java.lang.String")
					|| s.equalsIgnoreCase("java.lang.Double") || s.equalsIgnoreCase("java.lang.Boolean")
					|| s.equalsIgnoreCase("java.util.Date")) {
				continue;
			} else {
				throw new DBAppException("Invalid Data Type.");
			}
		}
		try {
			loadCSV(columnsnametype, tablename, key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File dir = new File(tablename);
		dir.mkdir();
		String firstPagedir = tablename + File.separator + "Page1.class";
		File f = new File(firstPagedir);
		f.getParentFile().mkdirs();
		f.createNewFile();
	}

	public static void loadCSV(Hashtable<String, String> columnsnametype, String tablename, String pk)
			throws IOException {

		PrintWriter pw = new PrintWriter(new FileWriter("metadata.csv", true));
		StringBuilder sb = new StringBuilder();

		sb.append("Table Name,Column Name,Column Type,Key,Indexed\n");
		Enumeration<String> enumKey = columnsnametype.keys();
		while (enumKey.hasMoreElements()) {
			String key = enumKey.nextElement();// name of table
			String val = columnsnametype.get(key);// type
			sb.append(tablename + "," + key + "," + val + "," + key.equals(pk) + "," + false + '\n');

		}
		sb.append(tablename + "," + "TouchDate" + "," + "java.util.Date" + "," + false + "," + false + '\n');
		pw.write(sb.toString());
		pw.close();
		// TODO remove the below line
		System.out.println("done!");
	}

	public static void insertIntoTable(String strTableName, Hashtable<String, Object> htblColNameValue)
			throws DBAppException, IOException {
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
				printTuples(table);
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class is non existant");
			c.printStackTrace();
			return;
		}
		int tmpInteger = -1;
		Object value = null;

		Enumeration<String> enumKey = htblColNameValue.keys();
		while (enumKey.hasMoreElements()) {
			String colName = enumKey.nextElement();// name of table
			Object val = htblColNameValue.get(colName);// value
			String valString = val.getClass().toString();// gets the type of the
			String[] typeArray = valString.split(" ");
			boolean flag = false;
			for (int i = 0; i < metadataArray.size(); i++) {
				String[] metaArray = metadataArray.get(i).split(",");
				if (metaArray[1].equals(colName)) {
					flag = true;
					if (typeArray[1].equalsIgnoreCase(metaArray[2])) {
						tuple.add(val);
						if (metaArray[3].equalsIgnoreCase("true")) {
							tmpInteger = i;
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
		tuple.add(new Date());

		for (Tuple t : table) {
			if (t.tupleData.get(tmpInteger).equals(value))
				throw new DBAppException("Primary key already exists");
		}
		tuple.setKey(value); // after checking that its a new key I'm setting
								// its value in tuple
		table.add(tuple);
		try {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;

			System.out.println("table size : " + table.size());
			int tableInitialSizeModN = table.size() / n;
			// System.out.println(tableInitialSizeModN);
			for (int i = 0; i <= tableInitialSizeModN; i++) {
				String path = strTableName + File.separator + "Page" + (i + 1) + ".class";
				// System.out.println(path);
				fileOut = new FileOutputStream(new File(path));

				out = new ObjectOutputStream(fileOut);
				PriorityQueue<Tuple> tableTmp = new PriorityQueue<Tuple>();
				// System.out.println(table.isEmpty());
				for (int k = 0; ((k % n != 0 || k == 0) && !(table.isEmpty())); k++) {
					// System.out.println("Before : "+initialQueueSize);

					tableTmp.add(table.remove());

					// System.out.println("j : "+j);
				}
				// System.out.println(i+"///"+j);

				out.writeObject(tableTmp);
			}
			// System.out.println(j);
			out.close();
			fileOut.close();
			System.out.println("Job Done");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void deleteFromTable(String strTableName, Hashtable<String, Object> htblColNameValue)
			throws DBAppException, IOException {
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
					}
					path = strTableName + File.separator + "Page" + i + ".class";
					File f = new File(path);
					if (f.isFile() && f.canRead()) {
						fileIn = new FileInputStream(path);
					} else
						break;
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
				printTuples(table);
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class is non existant");
			c.printStackTrace();
			return;
		}
		// =========================================================================
		int tmpInteger = -1;
		Object value = null;
		Enumeration<String> enumKey = htblColNameValue.keys();
		while (enumKey.hasMoreElements()) {
			String colName = enumKey.nextElement();// name of table
			Object val = htblColNameValue.get(colName);// value
			String valString = val.getClass().toString();// gets the type of the
			String[] typeArray = valString.split(" ");
			boolean flag = false;
			for (int i = 0; i < metadataArray.size(); i++) {
				String[] metaArray = metadataArray.get(i).split(",");
				if (metaArray[1].equals(colName)) {
					flag = true;
					if (typeArray[1].equalsIgnoreCase(metaArray[2])) {
						tuple.add(val);
						if (metaArray[3].equalsIgnoreCase("true")) {
							tmpInteger = i;
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
		try {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;
			System.out.println("table size : " + table.size());
			int tableInitialSizeModN = table.size() / n;
			for (int i = 0; i <= tableInitialSizeModN; i++) {
				String path = strTableName + File.separator + "Page" + (i + 1) + ".class";
				fileOut = new FileOutputStream(new File(path));

				out = new ObjectOutputStream(fileOut);
				PriorityQueue<Tuple> tableTmp = new PriorityQueue<Tuple>();
				for (int k = 0; ((k % n != 0 || k == 0) && !(table.isEmpty())); k++) {
					tableTmp.add(table.remove());
				}
				out.writeObject(tableTmp);
			}
			out.close();
			fileOut.close();
			System.out.println("Job Done");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	// ============================================================================
	public static void updateTable(String strTableName, String strKey, Hashtable<String, Object> htblColNameValue)
			throws DBAppException, IOException {
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
					}
					path = strTableName + File.separator + "Page" + i + ".class";
					File f = new File(path);
					if (f.isFile() && f.canRead()) {
						fileIn = new FileInputStream(path);
					} else
						break;
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
				//printTuples(table);
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class is non existant");
			c.printStackTrace();
			return;
		}
		// =========================================================================
		int tmpInteger = -1;
		Object value = null;
		Enumeration<String> enumKey = htblColNameValue.keys();
		while (enumKey.hasMoreElements()) {
			String colName = enumKey.nextElement();// name of table
			Object val = htblColNameValue.get(colName);// value
			String valString = val.getClass().toString();// gets the type of the
			String[] typeArray = valString.split(" ");
			boolean flag = false;
			for (int i = 0; i < metadataArray.size(); i++) {
				String[] metaArray = metadataArray.get(i).split(",");
				if (metaArray[1].equals(colName)) {
					flag = true;
					if (typeArray[1].equalsIgnoreCase(metaArray[2])) {
						tuple.add(val);
						if (metaArray[3].equalsIgnoreCase("true")) {
							tmpInteger = i;
							value = val;
							if(!metaArray[1].equals(strKey)){
							throw new DBAppException("The primary entered doesn't match");
							}
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
		// =========================================================================
		try {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;
			System.out.println("table size : " + table.size());
			int tableInitialSizeModN = table.size() / n;
			for (int i = 0; i <= tableInitialSizeModN; i++) {
				String path = strTableName + File.separator + "Page" + (i + 1) + ".class";
				fileOut = new FileOutputStream(new File(path));

				out = new ObjectOutputStream(fileOut);
				PriorityQueue<Tuple> tableTmp = new PriorityQueue<Tuple>();
				for (int k = 0; ((k % n != 0 || k == 0) && !(table.isEmpty())); k++) {
					tableTmp.add(table.remove());
				}
				out.writeObject(tableTmp);
			}
			out.close();
			fileOut.close();
			System.out.println("Job Done");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	// ============================================================================
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
}