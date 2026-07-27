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
import com.example.testudo.data.local.db.entity.ThreatLogEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
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
public final class ThreatLogDao_Impl implements ThreatLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ThreatLogEntity> __insertionAdapterOfThreatLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public ThreatLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfThreatLogEntity = new EntityInsertionAdapter<ThreatLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `threat_logs` (`id`,`scanHistoryId`,`packageName`,`appName`,`threatType`,`severity`,`detectionReason`,`detectedAt`,`confidenceScore`,`recommendedAction`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThreatLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getScanHistoryId());
        statement.bindString(3, entity.getPackageName());
        statement.bindString(4, entity.getAppName());
        statement.bindString(5, entity.getThreatType());
        statement.bindString(6, entity.getSeverity());
        statement.bindString(7, entity.getDetectionReason());
        statement.bindLong(8, entity.getDetectedAt());
        if (entity.getConfidenceScore() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getConfidenceScore());
        }
        if (entity.getRecommendedAction() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getRecommendedAction());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM threat_logs";
        return _query;
      }
    };
  }

  @Override
  public Object insertThreatLog(final ThreatLogEntity threatLog,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfThreatLogEntity.insertAndReturnId(threatLog);
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
  public Object getAllThreatLogs(final Continuation<? super List<ThreatLogEntity>> $completion) {
    final String _sql = "SELECT * FROM threat_logs ORDER BY detectedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ThreatLogEntity>>() {
      @Override
      @NonNull
      public List<ThreatLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfScanHistoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "scanHistoryId");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfThreatType = CursorUtil.getColumnIndexOrThrow(_cursor, "threatType");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDetectionReason = CursorUtil.getColumnIndexOrThrow(_cursor, "detectionReason");
          final int _cursorIndexOfDetectedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedAt");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfRecommendedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "recommendedAction");
          final List<ThreatLogEntity> _result = new ArrayList<ThreatLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThreatLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpScanHistoryId;
            _tmpScanHistoryId = _cursor.getLong(_cursorIndexOfScanHistoryId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpThreatType;
            _tmpThreatType = _cursor.getString(_cursorIndexOfThreatType);
            final String _tmpSeverity;
            _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            final String _tmpDetectionReason;
            _tmpDetectionReason = _cursor.getString(_cursorIndexOfDetectionReason);
            final long _tmpDetectedAt;
            _tmpDetectedAt = _cursor.getLong(_cursorIndexOfDetectedAt);
            final Float _tmpConfidenceScore;
            if (_cursor.isNull(_cursorIndexOfConfidenceScore)) {
              _tmpConfidenceScore = null;
            } else {
              _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            }
            final String _tmpRecommendedAction;
            if (_cursor.isNull(_cursorIndexOfRecommendedAction)) {
              _tmpRecommendedAction = null;
            } else {
              _tmpRecommendedAction = _cursor.getString(_cursorIndexOfRecommendedAction);
            }
            _item = new ThreatLogEntity(_tmpId,_tmpScanHistoryId,_tmpPackageName,_tmpAppName,_tmpThreatType,_tmpSeverity,_tmpDetectionReason,_tmpDetectedAt,_tmpConfidenceScore,_tmpRecommendedAction);
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
  public Object getThreatLogsForScan(final long scanHistoryId,
      final Continuation<? super List<ThreatLogEntity>> $completion) {
    final String _sql = "SELECT * FROM threat_logs WHERE scanHistoryId = ? ORDER BY detectedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, scanHistoryId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ThreatLogEntity>>() {
      @Override
      @NonNull
      public List<ThreatLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfScanHistoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "scanHistoryId");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfThreatType = CursorUtil.getColumnIndexOrThrow(_cursor, "threatType");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDetectionReason = CursorUtil.getColumnIndexOrThrow(_cursor, "detectionReason");
          final int _cursorIndexOfDetectedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedAt");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfRecommendedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "recommendedAction");
          final List<ThreatLogEntity> _result = new ArrayList<ThreatLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThreatLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpScanHistoryId;
            _tmpScanHistoryId = _cursor.getLong(_cursorIndexOfScanHistoryId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpThreatType;
            _tmpThreatType = _cursor.getString(_cursorIndexOfThreatType);
            final String _tmpSeverity;
            _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            final String _tmpDetectionReason;
            _tmpDetectionReason = _cursor.getString(_cursorIndexOfDetectionReason);
            final long _tmpDetectedAt;
            _tmpDetectedAt = _cursor.getLong(_cursorIndexOfDetectedAt);
            final Float _tmpConfidenceScore;
            if (_cursor.isNull(_cursorIndexOfConfidenceScore)) {
              _tmpConfidenceScore = null;
            } else {
              _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            }
            final String _tmpRecommendedAction;
            if (_cursor.isNull(_cursorIndexOfRecommendedAction)) {
              _tmpRecommendedAction = null;
            } else {
              _tmpRecommendedAction = _cursor.getString(_cursorIndexOfRecommendedAction);
            }
            _item = new ThreatLogEntity(_tmpId,_tmpScanHistoryId,_tmpPackageName,_tmpAppName,_tmpThreatType,_tmpSeverity,_tmpDetectionReason,_tmpDetectedAt,_tmpConfidenceScore,_tmpRecommendedAction);
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
