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
import com.example.testudo.data.local.db.entity.QuarantineRecordEntity;
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
public final class QuarantineDao_Impl implements QuarantineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QuarantineRecordEntity> __insertionAdapterOfQuarantineRecordEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public QuarantineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuarantineRecordEntity = new EntityInsertionAdapter<QuarantineRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `quarantine_records` (`id`,`threatLogId`,`packageName`,`appName`,`quarantinedAt`,`quarantineReason`,`actionStatus`,`evidenceSnapshotPath`,`permissionsRevoked`,`backgroundExecutionBlocked`,`isRestored`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QuarantineRecordEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getThreatLogId());
        statement.bindString(3, entity.getPackageName());
        statement.bindString(4, entity.getAppName());
        statement.bindLong(5, entity.getQuarantinedAt());
        statement.bindString(6, entity.getQuarantineReason());
        statement.bindString(7, entity.getActionStatus());
        if (entity.getEvidenceSnapshotPath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getEvidenceSnapshotPath());
        }
        final int _tmp = entity.getPermissionsRevoked() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.getBackgroundExecutionBlocked() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        final int _tmp_2 = entity.isRestored() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM quarantine_records";
        return _query;
      }
    };
  }

  @Override
  public Object insertQuarantineRecord(final QuarantineRecordEntity record,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfQuarantineRecordEntity.insertAndReturnId(record);
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
  public Object getAllQuarantineRecords(
      final Continuation<? super List<QuarantineRecordEntity>> $completion) {
    final String _sql = "SELECT * FROM quarantine_records ORDER BY quarantinedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<QuarantineRecordEntity>>() {
      @Override
      @NonNull
      public List<QuarantineRecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfThreatLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "threatLogId");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfQuarantinedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "quarantinedAt");
          final int _cursorIndexOfQuarantineReason = CursorUtil.getColumnIndexOrThrow(_cursor, "quarantineReason");
          final int _cursorIndexOfActionStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "actionStatus");
          final int _cursorIndexOfEvidenceSnapshotPath = CursorUtil.getColumnIndexOrThrow(_cursor, "evidenceSnapshotPath");
          final int _cursorIndexOfPermissionsRevoked = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionsRevoked");
          final int _cursorIndexOfBackgroundExecutionBlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "backgroundExecutionBlocked");
          final int _cursorIndexOfIsRestored = CursorUtil.getColumnIndexOrThrow(_cursor, "isRestored");
          final List<QuarantineRecordEntity> _result = new ArrayList<QuarantineRecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QuarantineRecordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpThreatLogId;
            _tmpThreatLogId = _cursor.getLong(_cursorIndexOfThreatLogId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final long _tmpQuarantinedAt;
            _tmpQuarantinedAt = _cursor.getLong(_cursorIndexOfQuarantinedAt);
            final String _tmpQuarantineReason;
            _tmpQuarantineReason = _cursor.getString(_cursorIndexOfQuarantineReason);
            final String _tmpActionStatus;
            _tmpActionStatus = _cursor.getString(_cursorIndexOfActionStatus);
            final String _tmpEvidenceSnapshotPath;
            if (_cursor.isNull(_cursorIndexOfEvidenceSnapshotPath)) {
              _tmpEvidenceSnapshotPath = null;
            } else {
              _tmpEvidenceSnapshotPath = _cursor.getString(_cursorIndexOfEvidenceSnapshotPath);
            }
            final boolean _tmpPermissionsRevoked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPermissionsRevoked);
            _tmpPermissionsRevoked = _tmp != 0;
            final boolean _tmpBackgroundExecutionBlocked;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBackgroundExecutionBlocked);
            _tmpBackgroundExecutionBlocked = _tmp_1 != 0;
            final boolean _tmpIsRestored;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsRestored);
            _tmpIsRestored = _tmp_2 != 0;
            _item = new QuarantineRecordEntity(_tmpId,_tmpThreatLogId,_tmpPackageName,_tmpAppName,_tmpQuarantinedAt,_tmpQuarantineReason,_tmpActionStatus,_tmpEvidenceSnapshotPath,_tmpPermissionsRevoked,_tmpBackgroundExecutionBlocked,_tmpIsRestored);
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
  public Object getQuarantineRecordsForPackage(final String packageName,
      final Continuation<? super List<QuarantineRecordEntity>> $completion) {
    final String _sql = "SELECT * FROM quarantine_records WHERE packageName = ? ORDER BY quarantinedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<QuarantineRecordEntity>>() {
      @Override
      @NonNull
      public List<QuarantineRecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfThreatLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "threatLogId");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfQuarantinedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "quarantinedAt");
          final int _cursorIndexOfQuarantineReason = CursorUtil.getColumnIndexOrThrow(_cursor, "quarantineReason");
          final int _cursorIndexOfActionStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "actionStatus");
          final int _cursorIndexOfEvidenceSnapshotPath = CursorUtil.getColumnIndexOrThrow(_cursor, "evidenceSnapshotPath");
          final int _cursorIndexOfPermissionsRevoked = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionsRevoked");
          final int _cursorIndexOfBackgroundExecutionBlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "backgroundExecutionBlocked");
          final int _cursorIndexOfIsRestored = CursorUtil.getColumnIndexOrThrow(_cursor, "isRestored");
          final List<QuarantineRecordEntity> _result = new ArrayList<QuarantineRecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QuarantineRecordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpThreatLogId;
            _tmpThreatLogId = _cursor.getLong(_cursorIndexOfThreatLogId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final long _tmpQuarantinedAt;
            _tmpQuarantinedAt = _cursor.getLong(_cursorIndexOfQuarantinedAt);
            final String _tmpQuarantineReason;
            _tmpQuarantineReason = _cursor.getString(_cursorIndexOfQuarantineReason);
            final String _tmpActionStatus;
            _tmpActionStatus = _cursor.getString(_cursorIndexOfActionStatus);
            final String _tmpEvidenceSnapshotPath;
            if (_cursor.isNull(_cursorIndexOfEvidenceSnapshotPath)) {
              _tmpEvidenceSnapshotPath = null;
            } else {
              _tmpEvidenceSnapshotPath = _cursor.getString(_cursorIndexOfEvidenceSnapshotPath);
            }
            final boolean _tmpPermissionsRevoked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPermissionsRevoked);
            _tmpPermissionsRevoked = _tmp != 0;
            final boolean _tmpBackgroundExecutionBlocked;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBackgroundExecutionBlocked);
            _tmpBackgroundExecutionBlocked = _tmp_1 != 0;
            final boolean _tmpIsRestored;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsRestored);
            _tmpIsRestored = _tmp_2 != 0;
            _item = new QuarantineRecordEntity(_tmpId,_tmpThreatLogId,_tmpPackageName,_tmpAppName,_tmpQuarantinedAt,_tmpQuarantineReason,_tmpActionStatus,_tmpEvidenceSnapshotPath,_tmpPermissionsRevoked,_tmpBackgroundExecutionBlocked,_tmpIsRestored);
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
