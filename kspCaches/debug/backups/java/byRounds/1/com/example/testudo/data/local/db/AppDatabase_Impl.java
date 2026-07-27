package com.example.testudo.data.local.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.testudo.data.local.db.dao.QuarantineDao;
import com.example.testudo.data.local.db.dao.QuarantineDao_Impl;
import com.example.testudo.data.local.db.dao.ScanHistoryDao;
import com.example.testudo.data.local.db.dao.ScanHistoryDao_Impl;
import com.example.testudo.data.local.db.dao.ThreatLogDao;
import com.example.testudo.data.local.db.dao.ThreatLogDao_Impl;
import com.example.testudo.data.local.db.dao.UserProfileDao;
import com.example.testudo.data.local.db.dao.UserProfileDao_Impl;
import com.example.testudo.data.local.db.dao.VirusSignatureDao;
import com.example.testudo.data.local.db.dao.VirusSignatureDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserProfileDao _userProfileDao;

  private volatile ScanHistoryDao _scanHistoryDao;

  private volatile ThreatLogDao _threatLogDao;

  private volatile QuarantineDao _quarantineDao;

  private volatile VirusSignatureDao _virusSignatureDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `paymentDetails` TEXT NOT NULL, `isPremium` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `scan_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `scannedAt` INTEGER NOT NULL, `riskScore` INTEGER NOT NULL, `riskLevel` TEXT NOT NULL, `behaviorSummary` TEXT NOT NULL, `actionTaken` TEXT NOT NULL, `isManualScan` INTEGER NOT NULL, `modelVersion` TEXT)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_scan_history_scannedAt` ON `scan_history` (`scannedAt`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_scan_history_packageName` ON `scan_history` (`packageName`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `threat_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `scanHistoryId` INTEGER NOT NULL, `packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `threatType` TEXT NOT NULL, `severity` TEXT NOT NULL, `detectionReason` TEXT NOT NULL, `detectedAt` INTEGER NOT NULL, `confidenceScore` REAL, `recommendedAction` TEXT, FOREIGN KEY(`scanHistoryId`) REFERENCES `scan_history`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_threat_logs_scanHistoryId` ON `threat_logs` (`scanHistoryId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_threat_logs_packageName` ON `threat_logs` (`packageName`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_threat_logs_detectedAt` ON `threat_logs` (`detectedAt`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `quarantine_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `threatLogId` INTEGER NOT NULL, `packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `quarantinedAt` INTEGER NOT NULL, `quarantineReason` TEXT NOT NULL, `actionStatus` TEXT NOT NULL, `evidenceSnapshotPath` TEXT, `permissionsRevoked` INTEGER NOT NULL, `backgroundExecutionBlocked` INTEGER NOT NULL, `isRestored` INTEGER NOT NULL, FOREIGN KEY(`threatLogId`) REFERENCES `threat_logs`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_quarantine_records_threatLogId` ON `quarantine_records` (`threatLogId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_quarantine_records_packageName` ON `quarantine_records` (`packageName`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_quarantine_records_quarantinedAt` ON `quarantine_records` (`quarantinedAt`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `virus_signatures` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `signatureHash` TEXT NOT NULL, `virusName` TEXT NOT NULL, `severity` TEXT NOT NULL, `description` TEXT, `recommendedAction` TEXT, `definitionVersion` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_virus_signatures_signatureHash` ON `virus_signatures` (`signatureHash`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_virus_signatures_virusName` ON `virus_signatures` (`virusName`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '84416736e2fb92afab2cfc3ab03c39ca')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        db.execSQL("DROP TABLE IF EXISTS `scan_history`");
        db.execSQL("DROP TABLE IF EXISTS `threat_logs`");
        db.execSQL("DROP TABLE IF EXISTS `quarantine_records`");
        db.execSQL("DROP TABLE IF EXISTS `virus_signatures`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(6);
        _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("paymentDetails", new TableInfo.Column("paymentDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("isPremium", new TableInfo.Column("isPremium", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.example.testudo.data.local.db.entity.UserProfileEntity).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsScanHistory = new HashMap<String, TableInfo.Column>(10);
        _columnsScanHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("scannedAt", new TableInfo.Column("scannedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("riskScore", new TableInfo.Column("riskScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("riskLevel", new TableInfo.Column("riskLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("behaviorSummary", new TableInfo.Column("behaviorSummary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("actionTaken", new TableInfo.Column("actionTaken", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("isManualScan", new TableInfo.Column("isManualScan", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("modelVersion", new TableInfo.Column("modelVersion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScanHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScanHistory = new HashSet<TableInfo.Index>(2);
        _indicesScanHistory.add(new TableInfo.Index("index_scan_history_scannedAt", false, Arrays.asList("scannedAt"), Arrays.asList("ASC")));
        _indicesScanHistory.add(new TableInfo.Index("index_scan_history_packageName", false, Arrays.asList("packageName"), Arrays.asList("ASC")));
        final TableInfo _infoScanHistory = new TableInfo("scan_history", _columnsScanHistory, _foreignKeysScanHistory, _indicesScanHistory);
        final TableInfo _existingScanHistory = TableInfo.read(db, "scan_history");
        if (!_infoScanHistory.equals(_existingScanHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "scan_history(com.example.testudo.data.local.db.entity.ScanHistoryEntity).\n"
                  + " Expected:\n" + _infoScanHistory + "\n"
                  + " Found:\n" + _existingScanHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsThreatLogs = new HashMap<String, TableInfo.Column>(10);
        _columnsThreatLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("scanHistoryId", new TableInfo.Column("scanHistoryId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("threatType", new TableInfo.Column("threatType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("detectionReason", new TableInfo.Column("detectionReason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("detectedAt", new TableInfo.Column("detectedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("confidenceScore", new TableInfo.Column("confidenceScore", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreatLogs.put("recommendedAction", new TableInfo.Column("recommendedAction", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysThreatLogs = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysThreatLogs.add(new TableInfo.ForeignKey("scan_history", "CASCADE", "NO ACTION", Arrays.asList("scanHistoryId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesThreatLogs = new HashSet<TableInfo.Index>(3);
        _indicesThreatLogs.add(new TableInfo.Index("index_threat_logs_scanHistoryId", false, Arrays.asList("scanHistoryId"), Arrays.asList("ASC")));
        _indicesThreatLogs.add(new TableInfo.Index("index_threat_logs_packageName", false, Arrays.asList("packageName"), Arrays.asList("ASC")));
        _indicesThreatLogs.add(new TableInfo.Index("index_threat_logs_detectedAt", false, Arrays.asList("detectedAt"), Arrays.asList("ASC")));
        final TableInfo _infoThreatLogs = new TableInfo("threat_logs", _columnsThreatLogs, _foreignKeysThreatLogs, _indicesThreatLogs);
        final TableInfo _existingThreatLogs = TableInfo.read(db, "threat_logs");
        if (!_infoThreatLogs.equals(_existingThreatLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "threat_logs(com.example.testudo.data.local.db.entity.ThreatLogEntity).\n"
                  + " Expected:\n" + _infoThreatLogs + "\n"
                  + " Found:\n" + _existingThreatLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsQuarantineRecords = new HashMap<String, TableInfo.Column>(11);
        _columnsQuarantineRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("threatLogId", new TableInfo.Column("threatLogId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("quarantinedAt", new TableInfo.Column("quarantinedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("quarantineReason", new TableInfo.Column("quarantineReason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("actionStatus", new TableInfo.Column("actionStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("evidenceSnapshotPath", new TableInfo.Column("evidenceSnapshotPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("permissionsRevoked", new TableInfo.Column("permissionsRevoked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("backgroundExecutionBlocked", new TableInfo.Column("backgroundExecutionBlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuarantineRecords.put("isRestored", new TableInfo.Column("isRestored", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuarantineRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysQuarantineRecords.add(new TableInfo.ForeignKey("threat_logs", "CASCADE", "NO ACTION", Arrays.asList("threatLogId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesQuarantineRecords = new HashSet<TableInfo.Index>(3);
        _indicesQuarantineRecords.add(new TableInfo.Index("index_quarantine_records_threatLogId", false, Arrays.asList("threatLogId"), Arrays.asList("ASC")));
        _indicesQuarantineRecords.add(new TableInfo.Index("index_quarantine_records_packageName", false, Arrays.asList("packageName"), Arrays.asList("ASC")));
        _indicesQuarantineRecords.add(new TableInfo.Index("index_quarantine_records_quarantinedAt", false, Arrays.asList("quarantinedAt"), Arrays.asList("ASC")));
        final TableInfo _infoQuarantineRecords = new TableInfo("quarantine_records", _columnsQuarantineRecords, _foreignKeysQuarantineRecords, _indicesQuarantineRecords);
        final TableInfo _existingQuarantineRecords = TableInfo.read(db, "quarantine_records");
        if (!_infoQuarantineRecords.equals(_existingQuarantineRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "quarantine_records(com.example.testudo.data.local.db.entity.QuarantineRecordEntity).\n"
                  + " Expected:\n" + _infoQuarantineRecords + "\n"
                  + " Found:\n" + _existingQuarantineRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsVirusSignatures = new HashMap<String, TableInfo.Column>(9);
        _columnsVirusSignatures.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("signatureHash", new TableInfo.Column("signatureHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("virusName", new TableInfo.Column("virusName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("recommendedAction", new TableInfo.Column("recommendedAction", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("definitionVersion", new TableInfo.Column("definitionVersion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVirusSignatures.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVirusSignatures = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVirusSignatures = new HashSet<TableInfo.Index>(2);
        _indicesVirusSignatures.add(new TableInfo.Index("index_virus_signatures_signatureHash", true, Arrays.asList("signatureHash"), Arrays.asList("ASC")));
        _indicesVirusSignatures.add(new TableInfo.Index("index_virus_signatures_virusName", false, Arrays.asList("virusName"), Arrays.asList("ASC")));
        final TableInfo _infoVirusSignatures = new TableInfo("virus_signatures", _columnsVirusSignatures, _foreignKeysVirusSignatures, _indicesVirusSignatures);
        final TableInfo _existingVirusSignatures = TableInfo.read(db, "virus_signatures");
        if (!_infoVirusSignatures.equals(_existingVirusSignatures)) {
          return new RoomOpenHelper.ValidationResult(false, "virus_signatures(com.example.testudo.data.local.db.entity.VirusSignatureEntity).\n"
                  + " Expected:\n" + _infoVirusSignatures + "\n"
                  + " Found:\n" + _existingVirusSignatures);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "84416736e2fb92afab2cfc3ab03c39ca", "2f7582e74626701d06cf7af5c24f8620");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_profile","scan_history","threat_logs","quarantine_records","virus_signatures");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `user_profile`");
      _db.execSQL("DELETE FROM `scan_history`");
      _db.execSQL("DELETE FROM `threat_logs`");
      _db.execSQL("DELETE FROM `quarantine_records`");
      _db.execSQL("DELETE FROM `virus_signatures`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ScanHistoryDao.class, ScanHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ThreatLogDao.class, ThreatLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(QuarantineDao.class, QuarantineDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VirusSignatureDao.class, VirusSignatureDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public ScanHistoryDao scanHistoryDao() {
    if (_scanHistoryDao != null) {
      return _scanHistoryDao;
    } else {
      synchronized(this) {
        if(_scanHistoryDao == null) {
          _scanHistoryDao = new ScanHistoryDao_Impl(this);
        }
        return _scanHistoryDao;
      }
    }
  }

  @Override
  public ThreatLogDao threatLogDao() {
    if (_threatLogDao != null) {
      return _threatLogDao;
    } else {
      synchronized(this) {
        if(_threatLogDao == null) {
          _threatLogDao = new ThreatLogDao_Impl(this);
        }
        return _threatLogDao;
      }
    }
  }

  @Override
  public QuarantineDao quarantineDao() {
    if (_quarantineDao != null) {
      return _quarantineDao;
    } else {
      synchronized(this) {
        if(_quarantineDao == null) {
          _quarantineDao = new QuarantineDao_Impl(this);
        }
        return _quarantineDao;
      }
    }
  }

  @Override
  public VirusSignatureDao virusSignatureDao() {
    if (_virusSignatureDao != null) {
      return _virusSignatureDao;
    } else {
      synchronized(this) {
        if(_virusSignatureDao == null) {
          _virusSignatureDao = new VirusSignatureDao_Impl(this);
        }
        return _virusSignatureDao;
      }
    }
  }
}
