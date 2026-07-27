package com.example.testudo.data.local.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.testudo.data.local.db.entity.ScanHistoryEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ScanHistoryDao_Impl implements ScanHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScanHistoryEntity> __insertionAdapterOfScanHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public ScanHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScanHistoryEntity = new EntityInsertionAdapter<ScanHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `scan_history` (`id`,`packageName`,`appName`,`scannedAt`,`riskScore`,`riskLevel`,`behaviorSummary`,`actionTaken`,`isManualScan`,`modelVersion`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScanHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPackageName());
        statement.bindString(3, entity.getAppName());
        statement.bindLong(4, entity.getScannedAt());
        statement.bindLong(5, entity.getRiskScore());
        statement.bindString(6, entity.getRiskLevel());
        statement.bindString(7, entity.getBehaviorSummary());
        statement.bindString(8, entity.getActionTaken());
        final int _tmp = entity.isManualScan() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getModelVersion() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getModelVersion());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM scan_history";
        return _query;
      }
    };
  }

  @Override
  public Object insertScanHistory(final ScanHistoryEntity scanHistory,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfScanHistoryEntity.insertAndReturnId(scanHistory);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllScanHistory(final Continuation<? super List<ScanHistoryEntity>> $completion) {
    final String _sql = "SELECT * FROM scan_history ORDER BY scannedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScanHistoryEntity>>() {
      @Override
      @NonNull
      public List<ScanHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scannedAt");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfBehaviorSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "behaviorSummary");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
          final int _cursorIndexOfIsManualScan = CursorUtil.getColumnIndexOrThrow(_cursor, "isManualScan");
          final int _cursorIndexOfModelVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "modelVersion");
          final List<ScanHistoryEntity> _result = new ArrayList<ScanHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScanHistoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final long _tmpScannedAt;
            _tmpScannedAt = _cursor.getLong(_cursorIndexOfScannedAt);
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpBehaviorSummary;
            _tmpBehaviorSummary = _cursor.getString(_cursorIndexOfBehaviorSummary);
            final String _tmpActionTaken;
            _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            final boolean _tmpIsManualScan;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsManualScan);
            _tmpIsManualScan = _tmp != 0;
            final String _tmpModelVersion;
            if (_cursor.isNull(_cursorIndexOfModelVersion)) {
              _tmpModelVersion = null;
            } else {
              _tmpModelVersion = _cursor.getString(_cursorIndexOfModelVersion);
            }
            _item = new ScanHistoryEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpScannedAt,_tmpRiskScore,_tmpRiskLevel,_tmpBehaviorSummary,_tmpActionTaken,_tmpIsManualScan,_tmpModelVersion);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getScanHistoryForPackage(final String packageName,
      final Continuation<? super List<ScanHistoryEntity>> $completion) {
    final String _sql = "SELECT * FROM scan_history WHERE packageName = ? ORDER BY scannedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScanHistoryEntity>>() {
      @Override
      @NonNull
      public List<ScanHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scannedAt");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfBehaviorSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "behaviorSummary");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
          final int _cursorIndexOfIsManualScan = CursorUtil.getColumnIndexOrThrow(_cursor, "isManualScan");
          final int _cursorIndexOfModelVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "modelVersion");
          final List<ScanHistoryEntity> _result = new ArrayList<ScanHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScanHistoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final long _tmpScannedAt;
            _tmpScannedAt = _cursor.getLong(_cursorIndexOfScannedAt);
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpBehaviorSummary;
            _tmpBehaviorSummary = _cursor.getString(_cursorIndexOfBehaviorSummary);
            final String _tmpActionTaken;
            _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            final boolean _tmpIsManualScan;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsManualScan);
            _tmpIsManualScan = _tmp != 0;
            final String _tmpModelVersion;
            if (_cursor.isNull(_cursorIndexOfModelVersion)) {
              _tmpModelVersion = null;
            } else {
              _tmpModelVersion = _cursor.getString(_cursorIndexOfModelVersion);
            }
            _item = new ScanHistoryEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpScannedAt,_tmpRiskScore,_tmpRiskLevel,_tmpBehaviorSummary,_tmpActionTaken,_tmpIsManualScan,_tmpModelVersion);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
