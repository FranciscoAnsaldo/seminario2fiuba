package eventhunter

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AlertListServiceSpec extends Specification {

    AlertListService alertListService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AlertList(...).save(flush: true, failOnError: true)
        //new AlertList(...).save(flush: true, failOnError: true)
        //AlertList alertList = new AlertList(...).save(flush: true, failOnError: true)
        //new AlertList(...).save(flush: true, failOnError: true)
        //new AlertList(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //alertList.id
    }

    void "test get"() {
        setupData()

        expect:
        alertListService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AlertList> alertListList = alertListService.list(max: 2, offset: 2)

        then:
        alertListList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        alertListService.count() == 5
    }

    void "test delete"() {
        Long alertListId = setupData()

        expect:
        alertListService.count() == 5

        when:
        alertListService.delete(alertListId)
        sessionFactory.currentSession.flush()

        then:
        alertListService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AlertList alertList = new AlertList()
        alertListService.save(alertList)

        then:
        alertList.id != null
    }
}
