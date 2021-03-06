package com.ale.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Objects;

/**
 * The type Excel fill cell merge strategy.
 *
 * @author alewu
 * @date 2020 /10/13
 */
public class ExcelFillCellMergeStrategy implements CellWriteHandler {
    private final int[] mergeColumnIndex;
    private final int mergeRowIndex;

    /**
     * Instantiates a new Excel fill cell merge strategy.
     */
    public ExcelFillCellMergeStrategy(int mergeRowIndex, int[] mergeColumnIndex) {
        this.mergeRowIndex = mergeRowIndex;
        this.mergeColumnIndex = mergeColumnIndex;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer integer, Boolean aBoolean) {

    }

//    @Override
//    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
//                                       CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

//    @Override
//    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
//                                 List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
//        int curRowIndex = cell.getRowIndex();
//        int curColIndex = cell.getColumnIndex();
//        if (curRowIndex > mergeRowIndex) {
//            for (int columnIndex : mergeColumnIndex) {
//                if (curColIndex == columnIndex) {
//                    mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
//                    break;
//                }
//            }
//        }
//    }

    /**
     * ???????????????????????????
     *
     * @param cell        ???????????????
     * @param curRowIndex ?????????
     * @param curColIndex ?????????
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() :
                cell.getNumericCellValue();
        Cell preCell = cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex);
        Object preData = preCell.getCellTypeEnum() == CellType.STRING ? preCell.getStringCellValue() :
                preCell.getNumericCellValue();
        // ?????????????????????????????????????????????????????????
        Boolean dataBool = preData.equals(curData);
        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        Boolean bool = Objects.equals(cell.getRow().getCell(0).getStringCellValue(),
                                      cell.getSheet().getRow(curRowIndex - 1).getCell(0).getStringCellValue());
        if (dataBool && bool) {
            Sheet sheet = writeSheetHolder.getSheet();
            List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
            boolean isMerged = false;
            for (int i = 0; i < mergeRegions.size() && !isMerged; i++) {
                CellRangeAddress cellRangeAddr = mergeRegions.get(i);
                // ??????????????????????????????????????????????????????????????????????????????????????????????????????
                if (cellRangeAddr.isInRange(curRowIndex - 1, curColIndex)) {
                    sheet.removeMergedRegion(i);
                    cellRangeAddr.setLastRow(curRowIndex);
                    sheet.addMergedRegion(cellRangeAddr);
                    isMerged = true;
                }
            }
            // ?????????????????????????????????????????????????????????
            if (!isMerged) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex,
                                                                         curColIndex);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }
}
