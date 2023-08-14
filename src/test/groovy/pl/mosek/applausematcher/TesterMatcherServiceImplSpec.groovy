package pl.mosek.applausematcher

import pl.mosek.applausematcher.domain.Device
import pl.mosek.applausematcher.exception.NotFoundException
import pl.mosek.applausematcher.repository.DeviceRepository
import pl.mosek.applausematcher.repository.TesterRepository
import pl.mosek.applausematcher.service.TesterMatcherServiceImpl
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TesterMatcherServiceImplSpec extends Specification {

    @Shared
    def deviceRepository = Stub(DeviceRepository)

    @Shared
    def testerRepository = Stub(TesterRepository)

    @Shared
    def testerMatcherService = new TesterMatcherServiceImpl(deviceRepository, testerRepository)

    @Shared
    def iphone4 = new Device(1, "iPhone 4", null, null)

    @Shared
    def nokia = new Device(2, "Nokia 3310", null, null)

    @Shared
    def macbook = new Device(3, "Macbook Pro", null, null)

    // This "tests" should be treated as a showcase, because of the project simplicity
    // Normally I would go for fully integrated tests using memory h2/test containers solution
    def "would throw NotFoundException"() {

        given:
        deviceRepository.findAllByDescriptionIn(inputDeviceDescriptions) >> dbDevices

        when:
        testerMatcherService.findMatchingTesters(["US", "JP"] as Set, inputDeviceDescriptions)

        then:
        thrown(NotFoundException)

        where:
        inputDeviceDescriptions                          | dbDevices
        ["iPhone 4", "Nokia 3310", "Macbook Pro"] as Set | [iphone4, nokia] as Set
        ["iPhone 4", "Nokia 3310", "Macbook Pro"] as Set | [iphone4] as Set
        ["iPhone 4", "Nokia 3310", "Macbook Pro"] as Set | [macbook] as Set
        ["iPhone 4", "Macbook Pro"] as Set               | [nokia, macbook] as Set
        ["Nokia 3310", "Macbook Pro"] as Set             | [iphone4] as Set
        ["Macbook Pro"] as Set                           | [iphone4] as Set
    }
}
