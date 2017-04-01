package com.goalieunionapps.grmacsfc;

import com.goalieunionapps.grmacsfc.Utils.UtilsTestSuite;
import com.goalieunionapps.grmacsfc.activities.admin.viewmodels.ViewModelTestSuite;
import com.goalieunionapps.grmacsfc.models.GameTest;
import com.goalieunionapps.grmacsfc.models.ModelsTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ModelsTestSuite.class,
        UtilsTestSuite.class,
        ViewModelTestSuite.class
})
public class AllTestsTestSuite {
}
