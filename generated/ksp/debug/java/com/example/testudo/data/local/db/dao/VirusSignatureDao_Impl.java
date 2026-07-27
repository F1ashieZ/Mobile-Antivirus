package com.example.testudo.data.local.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.testudo.data.local.db.entity.VirusSignatureEntity;
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
public final class VirusSignatureDao_Impl implements VirusSignatureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VirusSignatureEntity> __insertionAdapterOfVirusSignatureEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public VirusSignatureDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVirusSignatureEntity = new EntityInsertionAdapter<VirusSignatureEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `virus_signatures` (`id`,`signatureHash`,`virusName`,`severity`,`description`,`recommendedAction`,`definitionVersion`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VirusSignatureEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getSignatureHash());
        statement.bindString(3, entity.getVirusName());
        statement.bindString(4, entity.getSeverity());
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getRecommendedAction() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRecommendedAction());
        }
        if (entity.getDefinitionVersion() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDefinitionVersion());
        }
        statement.bindLong(8, entity.getCreatedAt());
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getUpdatedAt());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM virus_signatures";
        return _query;
      }
    };
  }

  @Override
  public Object insertVirusSignature(final VirusSignatureEntity signature,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfVirusSignatureEntity.insertAndReturnId(signature);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertVirusSignatures(final List<VirusSignatureEntity> signatures,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVirusSignatureEntity.insert(signatures);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
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
  public Object getAllVirusSignatures(
      final Continuation<? super List<VirusSignatureEntity>> $completion) {
    final String _sql = "SELECT * FROM virus_signatures ORDER BY virusName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VirusSignatureEntity>>() {
      @Override
      @NonNull
      public List<VirusSignatureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSignatureHash = CursorUtil.getColumnIndexOrThrow(_cursor, "signatureHash");
          final int _cursorIndexOfVirusName = CursorUtil.getColumnIndexOrThrow(_cursor, "virusName");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfRecommendedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "recommendedAction");
          final int _cursorIndexOfDefinitionVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "definitionVersion");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<VirusSignatureEntity> _result = new ArrayList<VirusSignatureEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VirusSignatureEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSignatureHash;
            _tmpSignatureHash = _cursor.getString(_cursorIndexOfSignatureHash);
            final String _tmpVirusName;
            _tmpVirusName = _cursor.getString(_cursorIndexOfVirusName);
            final String _tmpSeverity;
            _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpRecommendedAction;
            if (_cursor.isNull(_cursorIndexOfRecommendedAction)) {
              _tmpRecommendedAction = null;
            } else {
              _tmpRecommendedAction = _cursor.getString(_cursorIndexOfRecommendedAction);
            }
            final String _tmpDefinitionVersion;
            if (_cursor.isNull(_cursorIndexOfDefinitionVersion)) {
              _tmpDefinitionVersion = null;
            } else {
              _tmpDefinitionVersion = _cursor.getString(_cursorIndexOfDefinitionVersion);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _item = new VirusSignatureEntity(_tmpId,_tmpSignatureHash,_tmpVirusName,_tmpSeverity,_tmpDescription,_tmpRecommendedAction,_tmpDefinitionVersion,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getSignatureByHash(final String signatureHash,
      final Continuation<? super VirusSignatureEntity> $completion) {
    final String _sql = "SELECT * FROM virus_signatures WHERE signatureHash = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, signatureHash);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VirusSignatureEntity>() {
      @Override
      @Nullable
      public VirusSignatureEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSignatureHash = CursorUtil.getColumnIndexOrThrow(_cursor, "signatureHash");
          final int _cursorIndexOfVirusName = CursorUtil.getColumnIndexOrThrow(_cursor, "virusName");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfRecommendedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "recommendedAction");
          final int _cursorIndexOfDefinitionVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "definitionVersion");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final VirusSignatureEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSignatureHash;
            _tmpSignatureHash = _cursor.getString(_cursorIndexOfSignatureHash);
            final String _tmpVirusName;
            _tmpVirusName = _cursor.getString(_cursorIndexOfVirusName);
            final String _tmpSeverity;
            _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpRecommendedAction;
            if (_cursor.isNull(_cursorIndexOfRecommendedAction)) {
              _tmpRecommendedAction = null;
            } else {
              _tmpRecommendedAction = _cursor.getString(_cursorIndexOfRecommendedAction);
            }
            final String _tmpDefinitionVersion;
            if (_cursor.isNull(_cursorIndexOfDefinitionVersion)) {
              _tmpDefinitionVersion = null;
            } else {
              _tmpDefinitionVersion = _cursor.getString(_cursorIndexOfDefinitionVersion);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _result = new VirusSignatureEntity(_tmpId,_tmpSignatureHash,_tmpVirusName,_tmpSeverity,_tmpDescription,_tmpRecommendedAction,_tmpDefinitionVersion,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
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
