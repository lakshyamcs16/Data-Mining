package com.ivp.excel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Locale;


import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApachePOIExcelWrite {

    private static final String FILE_NAME = "D:\\AndroidUdacity\\Jars\\Excel Outputs\\Position Details.xlsx";
//  private static final String HEADER = "[{\"dataType\":\"String\",\"data\":\"Fund\",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Issuer\",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Security Name \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Long/Short \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Analyst \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Quantity EOD \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"GICS Industry Sub-Group \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"FactSet Sector Name \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Security Type \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Market Price ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Market Value ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Total Cost ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Average Cost ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Weight (%) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"DTD ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"MTD ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"QTD ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"YTD ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"ITD ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Delta Adj. Quantity \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Underlying Price \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Strategy \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Market Cap \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"RSC Sector \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Currency \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Custodian \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"RSC Sub Sector \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"Option Type \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"GICS Sector \",\"sID\":\"header\"}]";
//  private static final String DATA = "[[{\"data\":\"Four Pines Master Fund LP\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V Biosciences\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V BIOSCIENCES SERIES D (DUMMY TICKER)\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Long\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"931,725.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Biotechnology\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Common Stock\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.090000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"83,855.250000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"819,918.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.880000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.002900\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-736,062.750000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"931,725.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.090000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"3-V Biosciences / ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.068000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotech\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"USD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"MSPhys\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Private Investments\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Health Care\",\"dataType\":\"String\",\"sID\":\"stringBlack\"}],[{\"data\":\"Four Pines Master Fund LP\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V Biosciences\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V BIOSCIENCES SERIES E (DUMMY TICKER)\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Long\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"17,440,317.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Biotechnology\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Common Stock\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.092190\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"1,607,822.824000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"933,006.780000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.053497\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.056000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-0.000770\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-0.000770\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-0.000770\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-0.000770\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"674,816.044200\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"17,440,317.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.092190\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"3-V Biosciences / ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.068000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotech\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"USD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Private Investments\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Health Care\",\"dataType\":\"String\",\"sID\":\"stringBlack\"}],[{\"data\":\"Four Pines Master Fund LP\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V Biosciences\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V BIOSCIENCES SERIES D' (DUMMY TICKER)\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Long\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"1,909,184.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Biotechnology\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Common Stock\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.090000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"171,826.560000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"1,680,081.920000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.880000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.006000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-1,508,255.360000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"1,909,184.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.090000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"3-V Biosciences / ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.068000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotech\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"USD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"MSPhys\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Private Investments\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Health Care\",\"dataType\":\"String\",\"sID\":\"stringBlack\"}],[{\"data\":\"Four Pines Master Fund LP\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V Biosciences\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3V BIOSCIENCE D-1  PFD STK (DUMMY TICKER)\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Long\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"1,909,180.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Preferred Stock\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.002933\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"5,600.293153\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"5,600.300000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.002933\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000200\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-0.006847\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"1,909,180.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.002933\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"3-V Biosciences / ZB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.068000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotech\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"USD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Private Investments\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Health Care\",\"dataType\":\"String\",\"sID\":\"stringBlack\"}],[{\"data\":\"Four Pines Master Fund LP\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Achillion Pharmaceuticals Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"ACHILLION PHARMACEUTICALS ORD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Long\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"DG\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"3,350,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotechnology\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Biotechnology\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Common Stock\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"4.410000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"14,773,500.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"20,213,807.020000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"6.033972\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.514800\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-435,500.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"5,581,646.002000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"5,581,646.002000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"5,581,646.002000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-6,835,363.602000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"3,350,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"4.410000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Achillion / DG\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.611779\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotech\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"USD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"MSPB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Farm Team\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Health Care\",\"dataType\":\"String\",\"sID\":\"stringBlack\"}],[{\"data\":\"Four Pines Master Fund LP\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Aclaris Therapeutics Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"ACLARIS THERAPEUTICS ORD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Long\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"SS\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"2,100,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Pharmaceuticals\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Biotechnology\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Common Stock\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"1.280000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"2,688,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"21,584,466.150000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"10.278317\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"0.093700\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"-966,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-2,392,238.800000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-2,392,238.800000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-2,392,238.800000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"-8,256,758.560000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"},{\"data\":\"2,100,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"1.280000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Aclaris / SS\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"0.052829\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"},{\"data\":\"Biotech\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"USD\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"MSPB\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Farm Team\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"Health Care\",\"dataType\":\"String\",\"sID\":\"stringBlack\"}]]";
//  private static final String WNLDATA = "[[{\"dataType\":\"String\",\"data\":\"Issuer \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"MTD ($) \",\"sID\":\"header\"},{\"dataType\":\"String\",\"data\":\"MTD (bps) \",\"sID\":\"header\"}],[[{\"data\":\"Teleflex Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"2,732,900.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"9.523130\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Vertex Pharmaceuticals Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"2,600,885.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"9.063107\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Spark Therapeutics Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"1,303,857.180000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"4.543453\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"argenx SE\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"748,604.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"2.608604\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Intuitive Surgical Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"670,850.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"2.337660\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Odonate Therapeutics Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"632,100.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"2.202631\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Stryker Corp\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"562,060.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"1.958568\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Galapagos NV\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"552,306.375000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"1.924580\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Novocure Ltd\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"510,900.000000\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec0\"},{\"data\":\"1.780295\",\"dataType\":\"Number\",\"sID\":\"numberPositiveprec2\"}],[{\"data\":\"Jinxin Fertility Group Ltd\",\"dataType\":\"String\",\"sID\":\"lastRowString\"},{\"data\":\"466,835.019917\",\"dataType\":\"Number\",\"sID\":\"lastRowPprec0\"},{\"data\":\"1.626745\",\"dataType\":\"Number\",\"sID\":\"lastRowPprec2\"}]],[[{\"data\":\"GW Pharmaceuticals PLC\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-3,916,500.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-13.647532\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Amarin Corporation PLC\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-3,705,312.500000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-12.911622\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Insmed Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-2,944,359.898233\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-10.259988\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Acadia Healthcare Company Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-2,599,000.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-9.056539\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Blueprint Medicines Corp\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-2,091,975.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-7.289747\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"HCA Healthcare Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-1,806,350.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-6.294451\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Agios Pharmaceuticals Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-1,721,232.200000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-5.997848\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"IDEXX Laboratories Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-1,652,625.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-5.758777\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Portola Pharmaceuticals Inc\",\"dataType\":\"String\",\"sID\":\"stringBlack\"},{\"data\":\"-1,420,040.000000\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec0\"},{\"data\":\"-4.948306\",\"dataType\":\"Number\",\"sID\":\"numberNegativeprec2\"}],[{\"data\":\"Encompass Health Corp\",\"dataType\":\"String\",\"sID\":\"lastRowString\"},{\"data\":\"-1,251,900.000000\",\"dataType\":\"Number\",\"sID\":\"lastRowNprec0\"},{\"data\":\"-4.362401\",\"dataType\":\"Number\",\"sID\":\"lastRowNprec2\"}]]]";
    private static int rowNum = 0;
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Lakshya\\Desktop\\index.html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();
        content = content.substring(content.indexOf("<body>") + 6, content.indexOf("</body>"));
        System.out.println("Parsing data");     
//      System.out.println("Creating headers");
//      fillHeaderData(HEADER, sheet, workbook);
//      System.out.println("Adding data");
//      fillCellData(DATA, sheet, workbook, false);
        System.out.println("Creating Excel Sheet");
//		winnerAndLosers(WNLDATA, sheet, workbook);
        multisheets(content, workbook);
        System.out.println("Resizing columns");
        autoSizeColumns(workbook);  
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
    
    public static void autoSizeColumns(Workbook workbook) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(sheet.getFirstRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                }
            }
        }
    }
    
	public static void fillCellData(String data, XSSFSheet sheet, XSSFWorkbook workbook, boolean isEmptyRowIncluded) {
        JsonArray jsonArray = ApachePOIExcelWrite.getJsonArray(data);
                
        CellStyle style = workbook.createCellStyle();  
        Font font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex()); 
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        style.setFont(font);  
        
        CellStyle negstyle = workbook.createCellStyle();  
        Font negfont = workbook.createFont();
        negfont.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex()); 
        negstyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        negstyle.setFont(negfont);
        
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonArray obj = (JsonArray) jsonArray.get(i);
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            for (int j = 0; j < obj.size(); j++) {
            	JsonObject  jObj = obj.get(j).getAsJsonObject();            
                String sId = jObj.get("sID").getAsString();
                String dataType = jObj.get("dataType").getAsString();
                
                Cell cell = row.createCell(colNum++);
        		
                String stringField = jObj.get("data").getAsString();
        		
        		if(dataType.equalsIgnoreCase("Number")) {
        			NumberFormat format = NumberFormat.getInstance(Locale.US);
        			double dVal = 0;
					try {
						dVal = format.parse(stringField).doubleValue();
					} catch (ParseException e) {
						e.printStackTrace();
					}
        			cell.setCellValue(dVal);
        			switch(sId.toLowerCase()) { 
        				case "numberpositiveprec0":
        				case "lastrowpprec0":
	                	case "lastrowpprec2":
	                	case "numberpositiveprec2": cell.setCellStyle(style); break;
	                	case "lastrownprec2":
        				case "lastrownprec0":
        				case "numbernegativeprec0":
	                	case "numbernegativeprec2":	cell.setCellStyle(negstyle); break;
	                                        		
	                }
        		}else {
            		cell.setCellValue(stringField);
        		}
                                
            }             
        } 
        
        if(isEmptyRowIncluded) rowNum++;
    }
    
    public static void fillHeaderData(String data, XSSFSheet sheet, XSSFWorkbook workbook) {
        JsonArray obj = ApachePOIExcelWrite.getJsonArray(data);

        
        StylesTable stable = workbook.getStylesSource();
        XSSFCellStyle style = stable.createCellStyle();
        
        IndexedColorMap colorMap = workbook.getStylesSource().getIndexedColors();
        XSSFColor myColor = new XSSFColor(new java.awt.Color(24, 67, 102), colorMap);
        style.setFillForegroundColor(myColor);
        
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;

        for (int j = 0; j < obj.size(); j++) {
        	JsonObject  jObj = obj.get(j).getAsJsonObject();            
            
            Cell cell = row.createCell(colNum++);
            
            style.setFillForegroundColor(myColor);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            
            String stringField = jObj.get("data").getAsString();
    		cell.setCellValue(stringField);     
    		cell.setCellStyle(style);
        }
        
        sheet.createFreezePane(0, 1);
         
    }
    
    public static void winnerAndLosers(String data, XSSFSheet sheet, XSSFWorkbook workbook) {
    	
        JsonArray jsonArray = ApachePOIExcelWrite.getJsonArray(data);
        boolean isHeaderIncluded = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonArray obj = (JsonArray) jsonArray.get(i);
            
            if(!isHeaderIncluded) {
            	fillHeaderData(obj.toString(), sheet, workbook);
            	isHeaderIncluded = true;
            }else {
            	fillCellData(obj.toString(), sheet, workbook, true);
            }
            
            
        }
    }
    
    public static void multisheets(String data, XSSFWorkbook workbook) {
    	
        JsonArray jsonArray = ApachePOIExcelWrite.getJsonArray(data);
        JsonArray headerArray = (JsonArray) jsonArray.get(0);

        for (int i = 1; i < jsonArray.size(); i++) {    
            	JsonArray array = (JsonArray) jsonArray.get(i);
            	XSSFSheet sheet = workbook.createSheet(String.valueOf(i));
            	fillHeaderData(headerArray.toString(), sheet, workbook);            	
            	fillCellData(array.toString(), sheet, workbook, true);  
            	rowNum = 0;
        }
    }

	public static JsonArray getJsonArray(String data) {
    	JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(data);
        return jsonArray;
    }
}