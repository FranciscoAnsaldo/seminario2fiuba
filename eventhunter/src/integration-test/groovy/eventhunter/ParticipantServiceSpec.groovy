package eventhunter

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ParticipantServiceSpec extends Specification {

    ParticipantService participantService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Participant(...).save(flush: true, failOnError: true)
        //new Participant(...).save(flush: true, failOnError: true)
        //Participant participant = new Participant(...).save(flush: true, failOnError: true)
        //new Participant(...).save(flush: true, failOnError: true)
        //new Participant(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //participant.id
    }

    void "test get"() {
        setupData()

        expect:
        participantService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Participant> participantList = participantService.list(max: 2, offset: 2)

        then:
        participantList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        participantService.count() == 5
    }

    void "test delete"() {
        Long participantId = setupData()

        expect:
        participantService.count() == 5

        when:
        participantService.delete(participantId)
        sessionFactory.currentSession.flush()

        then:
        participantService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Participant participant = new Participant()
        participantService.save(participant)

        then:
        participant.id != null
    }
}
