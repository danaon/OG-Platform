/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Tests the OGComplexMatrix class 
 */
public class OGComplexMatrixTest {

  double[][] realdata4x3 = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } };
  double[] realdata4x3unwound = new double[] {1.00, 4.00, 7.00, 10.00, 2.00, 5.00, 8.00, 11.00, 3.00, 6.00, 9.00, 12.00 };

  double[][] imagdata4x3 = new double[][] { {10.00, 20.00, 30.00 }, {40.00, 50.00, 60.00 }, {70.00, 80.00, 90.00 }, {100.00, 110.00, 120.00 } };
  double[] imagdata4x3unwound = new double[] {10.00, 40.00, 70.00, 100.00, 20.00, 50.00, 80.00, 110.00, 30.00, 60.00, 90.00, 120.00 };

  double[] interleaved4x3 = new double[] {1.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00, 9.00, 90.00, 12.00,
      120.00 };

  ComplexType[][] cmplx4x3 = new ComplexType[][] { {new ComplexType(1, 10), new ComplexType(2, 20), new ComplexType(3, 30) },
      {new ComplexType(4, 40), new ComplexType(5, 50), new ComplexType(6, 60) }, {new ComplexType(7, 70), new ComplexType(8, 80), new ComplexType(9, 90) },
      {new ComplexType(10, 100), new ComplexType(11, 110), new ComplexType(12, 120) } };

  OGComplexMatrix getRow = new OGComplexMatrix(new double[] {7, 70, 8, 80, 9, 90 }, 1, 3);
  OGComplexMatrix getCol = new OGComplexMatrix(new double[] {2, 20, 5, 50, 8, 80, 11, 110 }, 4, 1);

  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGComplexMatrix(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGComplexMatrix(tmp);
  }

  // sending in null ptr double[][] double[][] constructor 
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorNullPtrFirstTest() {
    double[][] tmp1 = null;
    double[][] tmp2 = new double[2][3];
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in null ptr double[][] double[][] constructor 
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorNullPtrSecondTest() {
    double[][] tmp1 = new double[2][3];
    double[][] tmp2 = null;
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in embedded null ptr double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrInFirstTest() {
    double[][] tmp1 = new double[2][];
    tmp1[0] = new double[] {1 };
    tmp1[1] = null;
    double[][] tmp2 = new double[2][1];
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in embedded null ptr double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrInSecondTest() {
    double[][] tmp2 = new double[2][];
    double[][] tmp1 = new double[2][];
    tmp1[0] = new double[] {1 };
    tmp1[1] = null;
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in null ptr double[] constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrConstructorNullPtrTest() {
    double[] tmp = null;
    new OGComplexMatrix(tmp, 1, 1);
  }

  // sending in null ptr double[] double [] int int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrConstructorNullPtrFirstTest() {
    double[] tmp1 = null;
    double[] tmp2 = new double[1];
    new OGComplexMatrix(tmp1, tmp2, 1, 1);
  }

  // sending in null ptr double[] double [] int int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrConstructorNullPtrSecondTest() {
    double[] tmp1 = new double[1];
    double[] tmp2 = null;
    new OGComplexMatrix(tmp1, tmp2, 1, 1);
  }

  // sending in null ptr ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypePtrPtrConstructorNullPtrTest() {
    ComplexType[][] tmp = null;
    new OGComplexMatrix(tmp);
  }

  // sending in embedded null ptr ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypePtrPtrConstructorOKThenNullPtrTest() {
    ComplexType[][] tmp = new ComplexType[2][1];
    tmp[0] = new ComplexType[] {new ComplexType(1) };
    tmp[1] = null;
    new OGComplexMatrix(tmp);
  }

  // sending in null ptr ComplexType constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypeConstructorNullPtrTest() {
    ComplexType tmp = new ComplexType(0);
    tmp = null;
    new OGComplexMatrix((ComplexType) tmp);
  }

  // sending in ragged[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGComplexMatrix(tmp);
  }

  // sending in ragged[][] first double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRaggedFirstTest() {
    double[][] tmp1 = new double[2][];
    double[][] tmp2 = new double[2][2];
    tmp1[0] = new double[] {1 };
    tmp1[1] = new double[] {1, 2, 3 };
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in ragged[][] second double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRaggedSecondTest() {
    double[][] tmp1 = new double[2][2];
    double[][] tmp2 = new double[2][];
    tmp2[0] = new double[] {1 };
    tmp2[1] = new double[] {1, 2, 3 };
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in ragged ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testComplexTypePtrPtrConstructorRaggedTest() {
    ComplexType[][] tmp = new ComplexType[2][1];
    tmp[0] = new ComplexType[] {new ComplexType(1), new ComplexType(2) };
    tmp[1] = new ComplexType[] {new ComplexType(1) };
    new OGComplexMatrix(tmp);
  }

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorZeroInterleavedSinglePointer(realdata4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in ok double[][] double[][] constructor
  @Test
  public void testDoublePtrPtrDoublePtrPtrConstructorInternalDataTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3, imagdata4x3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoRowMajorDoublePointerToColumnMajorInterleavedSinglePointer(realdata4x3, imagdata4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGComplexMatrix(realdata4x3unwound, -1, 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGComplexMatrix(realdata4x3unwound, 3, -1);
  }

  //sending in bad data count double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGComplexMatrix(realdata4x3unwound, 3, 17);
  }

  //sending in ok REAL double[] constructor
  @Test
  public void testDoublePtrConstructorRealDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(realdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in ok REAL double[] constructor, interleaved data branch
  @Test
  public void testDoublePtrConstructorInterleavedDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realdata4x3unwound, imagdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows double[] double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorBadRowsDataTest() {
    new OGComplexMatrix(realdata4x3unwound, imagdata4x3unwound, -1, 3);
  }

  //sending in bad rows double[] double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorBadColsDataTest() {
    new OGComplexMatrix(realdata4x3unwound, imagdata4x3unwound, 3, -1);
  }

  //sending in bad data count double[] double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorDataCountDataTest() {
    new OGComplexMatrix(realdata4x3unwound, imagdata4x3unwound, 3, 17);
  }

  //sending in bad data lengths double[] double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorBadDataLengthTest() {
    new OGComplexMatrix(realdata4x3unwound, new double[4], 3, 17);
  }

  //sending in ok double[] double[] constructor
  @Test
  public void testDoublePtrDoublePtrConstructorRealDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3unwound, imagdata4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realdata4x3unwound, imagdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  @Test
  // sending in single double for a 1x1 element array
  public void testDoubleConstructorInternalDataTest() {
    OGComplexMatrix D = new OGComplexMatrix((double) 7.);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {7., 0 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // sending in null ptr ComplexType constructor
  @Test
  public void testComplexTypeConstructorInternalDataTest() {
    ComplexType tmp = new ComplexType(7.0);
    OGComplexMatrix D = new OGComplexMatrix(tmp);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {7., 0 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);

  }

  //sending in ok ComlpexType[][] constructor, interleaved data branch
  @Test
  public void testComplexTypePtrPtrConstructorDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(cmplx4x3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realdata4x3unwound, imagdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j).getReal() == realdata4x3[i][j]);
        assertTrue(D.getEntry(i, j).getImag() == imagdata4x3[i][j]);
      }
    }
  }

  // test get entry ok
  @Test
  public void testGetEntryOKLinearIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    for (int j = 0; j < D.getNumberOfColumns(); j++) {
      for (int i = 0; i < D.getNumberOfRows(); i++) {
        assertTrue(D.getEntry(i + j * D.getNumberOfRows()).getReal() == realdata4x3[i][j]);
        assertTrue(D.getEntry(i + j * D.getNumberOfRows()).getImag() == imagdata4x3[i][j]);
      }
    }
  }

  // test set entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadRowIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.setEntry(23, 1, 1);
  }

  // test set entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadColumnIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.setEntry(1, 23, 1);
  }

  // test set null Number
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testSetEntryNullNumberTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.setEntry(1, 1, null);
  }

  // test set entry ok
  @Test
  public void testSetEntryOKIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    Number val = new ComplexType(13, 37);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        Number tmp = D.setEntry(i, j, val).getEntry(i, j);
        assertTrue(((ComplexType) tmp).getReal() == ((ComplexType) val).getReal());
        assertTrue(((ComplexType) tmp).getImag() == ((ComplexType) val).getImag());
      }
    }
    // make sure the underlying data is now all val
    double[] dataFinal = D.getData();
    for (int i = 0; i < dataFinal.length; i += 2) {
      assertTrue(dataFinal[i] == ((ComplexType) val).getReal());
      assertTrue(dataFinal[i + 1] == ((ComplexType) val).getImag());
    }
  }

  // test real value assigned ok
  @Test
  public void testSetEntryRealNumberAssignmentTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    Number val = 1337;
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        Number tmp = D.setEntry(i, j, val).getEntry(i, j);
        assertTrue(((ComplexType) tmp).getReal() == val.doubleValue());
        assertTrue(((ComplexType) tmp).getImag() == 0.d);
      }
    }
    // make sure the underlying data is now all val
    double[] dataFinal = D.getData();
    for (int i = 0; i < dataFinal.length; i += 2) {
      assertTrue(dataFinal[i] == val.doubleValue());
      assertTrue(dataFinal[i + 1] == 0d);
    }
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullRow(-1);
  }

  // test get full row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullRow(23);
  }

  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getRow(-1);
  }

  // test get full row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getRow(23);
  }

  // test get full row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix row = D.getRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get full col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullColumn(-1);
  }

  // test get full col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullColumn(23);
  }

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getColumn(-1);
  }

  // test get col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getColumn(23);
  }

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGArray<? extends Number> col = D.getColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get cols null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNullTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getColumns(null);
  }

  // test get cols neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getColumns(new int[] {-1 });
  }

  // test get cols bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getColumn(23);
  }

  // test get cols ok
  @Test
  public void testGetConsecutiveColumnsOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    double[][] rp = new double[][] { {2., 3. }, {5., 6. }, {8., 9. }, {11., 12. } };
    double[][] ip = new double[][] { {20., 30. }, {50., 60. }, {80., 90. }, {110., 120. } };
    OGComplexMatrix expected = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> col = D.getColumns(new int[] {1, 2 });
    assertTrue(col.equals(expected));
  }

  // test get cols ok
  @Test
  public void testGetRandomColumnsOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    double[][] rp = new double[][] { {3., 1. }, {6., 4. }, {9., 7. }, {12., 10. } };
    double[][] ip = new double[][] { {30., 10. }, {60., 40. }, {90., 70. }, {120., 100. } };
    OGComplexMatrix expected = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> col = D.getColumns(new int[] {2, 0 });
    assertTrue(col.equals(expected));
  }

  // test get Rows null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNullTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getRows(null);
  }

  // test get Rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getRows(new int[] {-1 });
  }

  // test get Rows bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getRows(23);
  }

  // test get Rows ok
  @Test
  public void testGetConsecutiveRowsOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    double[][] rp = new double[][] { {4., 5., 6. }, {7., 8., 9. } };
    double[][] ip = new double[][] { {40., 50., 60. }, {70., 80., 90. } };
    OGComplexMatrix expected = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> row = D.getRows(new int[] {1, 2 });
    assertTrue(row.equals(expected));
  }

  // test get cols ok
  @Test
  public void testGetRandomRowsOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    double[][] rp = new double[][] { {7., 8., 9. }, {1., 2., 3. } };
    double[][] ip = new double[][] { {70., 80., 90. }, {10., 20., 30. } };
    OGComplexMatrix expected = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> row = D.getRows(new int[] {2, 0 });
    assertTrue(row.equals(expected));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.get(null, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.get(new int[] {1, 2, 3 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.get(new int[] {-1 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.get(new int[] {23 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {23 });
  }

  @Test
  public void testGetSeqRowsSeqColsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {1, 2 });
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {5, 50, 8, 80, 11, 110, 6, 60, 9, 90, 12, 120 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetSeqRowsRandomColsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {2, 0 });
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {6, 60, 9, 90, 12, 120, 4, 40, 7, 70, 10, 100 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRandomRowsRandomColsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {0, 3, 2 }, new int[] {2, 0 });
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {3, 30, 12, 120, 9, 90, 1, 10, 10, 100, 7, 70 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getNumberOfElements() == 12);
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(Arrays.equals(D.getData(), interleaved4x3));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(7);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[][] {{1, 2, 3 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[] {999.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00,
        9.00, 90.00, 12.00, 120.00 }, 4, 3);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[] {1.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00,
        9.00, 90.00, 12.00, 120.00 }, 4, 3);
    assertTrue(D.equals(Diff));
  }

  // test equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.fuzzyequals(D, 10 * D1mach.four()));
  }

  // test equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.fuzzyequals(null, 10 * D1mach.four()));
  }

  // test equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1mach.four()));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(7);
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[][] {{1, 2, 3 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[] {999.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00,
        9.00, 90.00, 12.00, 120.00 }, 4, 3);
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[] {1.00 + 9 * D1mach.four(), 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00,
        30.00, 6.00, 60.00, 9.00, 90.00, 12.00, 120.00 }, 4, 3);
    assertTrue(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.hashCode();
  }

  // test toString code
  @Test
  public void testToStringTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.toString();
  }

  @Test
  public void parserLinearModeTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix expected;
    double[][] re = new double[][] {{4., 7., 10. } };
    double[][] im = new double[][] {{40., 70., 100. } };

    expected = new OGComplexMatrix(re, im);
    assertTrue(expected.fuzzyequals(D.get("1:3"), 10 * D1mach.four()));

    expected = new OGComplexMatrix(interleaved4x3, 1, 12);
    assertTrue(expected.fuzzyequals(D.get(":"), 10 * D1mach.four()));
  }

  @Test
  public void parser2DModeTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix expected;
    double[][] re = new double[][] { {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
    double[][] im = new double[][] { {40., 50., 60. }, {70., 80., 90. }, {100., 110., 120. } };
    expected = new OGComplexMatrix(re, im);
    assertTrue(expected.fuzzyequals(D.get("1:3,:"), 10 * D1mach.four()));

    re = new double[][] { {2., 3. }, {5., 6. }, {8., 9. }, {11., 12. } };
    im = new double[][] { {20., 30. }, {50., 60. }, {80., 90. }, {110., 120. } };
    expected = new OGComplexMatrix(re, im);
    assertTrue(expected.fuzzyequals(D.get(":,1:2"), 10 * D1mach.four()));

    expected = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(expected.fuzzyequals(D.get(":,:"), 10 * D1mach.four()));

  }

}
