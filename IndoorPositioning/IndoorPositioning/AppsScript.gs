/**
 * Google Apps Script — Indoor Positioning Data Receiver
 * Deploy as Web App → Anyone → get URL → paste into SheetsLogger.java
 */
function doPost(e) {
  try {
    var sheet = SpreadsheetApp.getActiveSpreadsheet()
                              .getSheetByName("Data");
    if (!sheet) {
      sheet = SpreadsheetApp.getActiveSpreadsheet().insertSheet("Data");
      sheet.appendRow([
        "timestamp", "landmark", "bssid",
        "distance_mm", "stddev_mm", "rssi_dbm",
        "success_frames", "attempt_frames", "type",
        "ap_x", "ap_y"
      ]);
      sheet.setFrozenRows(1);
      sheet.getRange(1,1,1,11).setFontWeight("bold");
    }

    var d = JSON.parse(e.postData.contents);
    sheet.appendRow([
      d.timestamp, d.landmark, d.bssid,
      d.distance_mm, d.stddev_mm, d.rssi_dbm,
      d.success_frames, d.attempt_frames, d.type,
      d.ap_x, d.ap_y
    ]);

    return ContentService
      .createTextOutput(JSON.stringify({status:"ok", rows: sheet.getLastRow()}))
      .setMimeType(ContentService.MimeType.JSON);

  } catch(err) {
    return ContentService
      .createTextOutput(JSON.stringify({status:"error", message: err.toString()}))
      .setMimeType(ContentService.MimeType.JSON);
  }
}
