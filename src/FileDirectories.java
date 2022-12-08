import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileDirectories {

    private static class Item {
        String name;
        long size;

        public long calculateSize() {
            return size;
        }

        public long getSize() {
            return size;
        }
    }
    private static class Directory extends Item {
        List<Item> contents;
        Directory parent;

        public Directory(String name, List<Item> contents, Directory parent) {
            this.name = name;
            this.contents = contents;
            this.parent = parent;
        }

        public long calculateSize(){
            this.size = contents.stream().map(Item::calculateSize).reduce(0L, Long::sum);
            return this.size;
        }

        @Override
        public String toString(){
            return "- " + this.name + " (dir)\n\t" + contents + "\n";
        }
    }
    private static class File extends Item {

        public File(long size, String name) {
            this.size = size;
            this.name = name;
        }

        @Override
        public String toString() {
            return "- " + this.name + "(file, size=" + this.size + ")";
        }
    }

    public static void main(String[] args) {
        String[] commandsAndOutput = INPUT.split("\n");
        String rootDirName = "/";
        Directory root = new Directory(rootDirName, new ArrayList<>(), null);
        Directory currentDir = root;
        List<Directory> allDirectories = new ArrayList<>();
        allDirectories.add(root);
        for(int i = 0; i < commandsAndOutput.length; i++){
            String command = commandsAndOutput[i];
            String[] commandParts = command.split(" ");
            if("cd".equals(commandParts[1])) {
                String commandDirName = commandParts[2];
                if(commandDirName.equals(rootDirName)){
                    currentDir = root;
                }
                else if(commandDirName.equals("..")){
                    currentDir = currentDir.parent;
                }
                else {
                    Optional<Item> found = currentDir.contents.stream().filter((item) -> commandDirName.equals(item.name) && item instanceof Directory).findFirst();
                    if(found.isPresent()){
                        currentDir = (Directory) found.get();
                    } else {
                        Directory childDir = new Directory(commandDirName, new ArrayList<>(), currentDir);
                        currentDir.contents.add(childDir);
                        currentDir = childDir;
                        allDirectories.add(currentDir);
                    }

                }
            } else {
                List<Item> contents = new ArrayList<>();
                while(i + 1 < commandsAndOutput.length && !commandsAndOutput[i + 1].startsWith("$")){
                    String[] itemParts = commandsAndOutput[i + 1].split(" ");
                    if("dir".equals(itemParts[0])){
                        String dirName = itemParts[1];
                        if(currentDir.contents.stream().noneMatch((item) -> dirName.equals(item.name) && item instanceof Directory)){
                            Directory directory = new Directory(dirName, new ArrayList<>(), currentDir);
                            contents.add(directory);
                            allDirectories.add(directory);
                        }
                    } else {
                        String fileName = itemParts[1];
                        if(currentDir.contents.stream().noneMatch((item) -> fileName.equals(item.name) && item instanceof File)){
                            contents.add(new File(Long.parseLong(itemParts[0]), fileName));
                        }
                    }
                    i++;
                }
                currentDir.contents.addAll(contents);
            }
        }

        System.out.println(root);

        root.calculateSize();

        int sizeSum = 0;
        for(Directory dir : allDirectories){
            System.out.println(dir.name + " has size " + dir.getSize());
            if(dir.getSize() <= 100000){
                sizeSum += dir.getSize();
            }
        }

        System.out.println(sizeSum);

        long spaceNeededToFree = 30000000 - (70000000 - root.size);

        allDirectories.sort((d1, d2) -> (int) (d1.size - d2.size));
        System.out.println(allDirectories.stream().filter(dir -> dir.size > spaceNeededToFree).findFirst().get().size);

    }

    private static final String INPUT =
            "$ cd /\n" +
                    "$ ls\n" +
                    "dir fnsvfbzt\n" +
                    "dir hqdssf\n" +
                    "dir jwphbz\n" +
                    "dir lncqsmj\n" +
                    "dir mhqs\n" +
                    "dir trwqgzsb\n" +
                    "132067 vjw\n" +
                    "dir wbsph\n" +
                    "$ cd fnsvfbzt\n" +
                    "$ ls\n" +
                    "62158 sfwnts.hbj\n" +
                    "$ cd ..\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "45626 cvcbmcm\n" +
                    "dir dlsmjsbz\n" +
                    "dir hqdssf\n" +
                    "dir mhqs\n" +
                    "dir mtw\n" +
                    "dir sfccfsrd\n" +
                    "dir shzgg\n" +
                    "$ cd dlsmjsbz\n" +
                    "$ ls\n" +
                    "9205 qcqbgd.lzd\n" +
                    "$ cd ..\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "105963 mhqs.zrn\n" +
                    "87909 slwshm.nwr\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "dir ctfl\n" +
                    "45923 jvvl.rcs\n" +
                    "dir jzjm\n" +
                    "dir lncqsmj\n" +
                    "dir mhqs\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd ctfl\n" +
                    "$ ls\n" +
                    "dir shzgg\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "18097 cvcbmcm\n" +
                    "289064 mhqs\n" +
                    "208557 slwshm.nwr\n" +
                    "283449 vjw\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "263560 dssbpgnl.szh\n" +
                    "dir hnqjmq\n" +
                    "76551 jvvl.rcs\n" +
                    "195911 lncqsmj\n" +
                    "185776 slwshm.nwr\n" +
                    "$ cd hnqjmq\n" +
                    "$ ls\n" +
                    "3307 rjd.lgh\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd jzjm\n" +
                    "$ ls\n" +
                    "31719 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "dir mhqs\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "138296 wfbvtfmr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "175499 jvvl.rcs\n" +
                    "dir rqznrr\n" +
                    "108476 slwshm.nwr\n" +
                    "199853 vjw\n" +
                    "$ cd rqznrr\n" +
                    "$ ls\n" +
                    "105075 shslmlt.spg\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "131522 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mtw\n" +
                    "$ ls\n" +
                    "dir dcgpfrsf\n" +
                    "dir gwqm\n" +
                    "188911 hqdssf\n" +
                    "34693 jvvl.rcs\n" +
                    "dir shzgg\n" +
                    "$ cd dcgpfrsf\n" +
                    "$ ls\n" +
                    "47863 dfwthflp.jwq\n" +
                    "203815 dqbqbps.prq\n" +
                    "dir mhqs\n" +
                    "183653 rqjqpm.bbr\n" +
                    "220694 vjw\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "74450 tjpn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd gwqm\n" +
                    "$ ls\n" +
                    "dir trghjhvs\n" +
                    "$ cd trghjhvs\n" +
                    "$ ls\n" +
                    "dir shzgg\n" +
                    "2620 slwshm.nwr\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "73435 shzgg.bbf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "184378 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd sfccfsrd\n" +
                    "$ ls\n" +
                    "87317 ccvs.phq\n" +
                    "dir cqmgf\n" +
                    "dir cssmfv\n" +
                    "dir hhhqdmz\n" +
                    "17971 mhqs\n" +
                    "dir mvjttqr\n" +
                    "264394 slwshm.nwr\n" +
                    "dir stdzptb\n" +
                    "dir tcddd\n" +
                    "dir zmrt\n" +
                    "dir zqqtb\n" +
                    "$ cd cqmgf\n" +
                    "$ ls\n" +
                    "202804 tcrqqlgs\n" +
                    "$ cd ..\n" +
                    "$ cd cssmfv\n" +
                    "$ ls\n" +
                    "13440 jnhzrtfd\n" +
                    "$ cd ..\n" +
                    "$ cd hhhqdmz\n" +
                    "$ ls\n" +
                    "dir fdbpld\n" +
                    "272827 jftd.lml\n" +
                    "dir prqbhbv\n" +
                    "dir wbzvmz\n" +
                    "$ cd fdbpld\n" +
                    "$ ls\n" +
                    "dir bgrnz\n" +
                    "dir dcdj\n" +
                    "78284 jqqfc.tzz\n" +
                    "147731 lbdsh.plp\n" +
                    "269235 ntf.gmq\n" +
                    "$ cd bgrnz\n" +
                    "$ ls\n" +
                    "dir qzs\n" +
                    "dir rcv\n" +
                    "dir wmrmhdd\n" +
                    "$ cd qzs\n" +
                    "$ ls\n" +
                    "156779 lncqsmj.gnf\n" +
                    "$ cd ..\n" +
                    "$ cd rcv\n" +
                    "$ ls\n" +
                    "224927 dvbw.svf\n" +
                    "dir hqdssf\n" +
                    "dir jjmsqft\n" +
                    "255189 mhqs.pcl\n" +
                    "dir mvgqtlm\n" +
                    "dir pjs\n" +
                    "dir sljw\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "63099 blpdwd\n" +
                    "96035 jvvl.rcs\n" +
                    "40533 lvpsmzw\n" +
                    "205031 rjjrg.pjq\n" +
                    "59874 shzgg.ldg\n" +
                    "dir zdnfpwlw\n" +
                    "$ cd zdnfpwlw\n" +
                    "$ ls\n" +
                    "109325 hqdssf.fmj\n" +
                    "41775 jfqhq.tdn\n" +
                    "203744 nprghch.zjb\n" +
                    "dir qfs\n" +
                    "dir rzctqrgm\n" +
                    "dir shzgg\n" +
                    "$ cd qfs\n" +
                    "$ ls\n" +
                    "234449 vjw\n" +
                    "$ cd ..\n" +
                    "$ cd rzctqrgm\n" +
                    "$ ls\n" +
                    "99041 cvcbmcm\n" +
                    "$ cd ..\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "149752 mhqs.bds\n" +
                    "126331 rjjrg.pjq\n" +
                    "75464 vbcjqdjv\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd jjmsqft\n" +
                    "$ ls\n" +
                    "47522 shzgg\n" +
                    "$ cd ..\n" +
                    "$ cd mvgqtlm\n" +
                    "$ ls\n" +
                    "dir tgtbj\n" +
                    "dir tmrsn\n" +
                    "dir vpv\n" +
                    "$ cd tgtbj\n" +
                    "$ ls\n" +
                    "86585 lbmv.jcr\n" +
                    "202755 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd tmrsn\n" +
                    "$ ls\n" +
                    "150980 hqdssf.hmh\n" +
                    "$ cd ..\n" +
                    "$ cd vpv\n" +
                    "$ ls\n" +
                    "197783 jvvl.rcs\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd pjs\n" +
                    "$ ls\n" +
                    "216539 cvcbmcm\n" +
                    "159509 jgfphj\n" +
                    "195297 pdgnb.qjd\n" +
                    "162974 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd sljw\n" +
                    "$ ls\n" +
                    "61689 hqdssf.bbn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wmrmhdd\n" +
                    "$ ls\n" +
                    "92888 msgcqfbf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd dcdj\n" +
                    "$ ls\n" +
                    "99280 mclcnw\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd prqbhbv\n" +
                    "$ ls\n" +
                    "102589 bqlcds.pqp\n" +
                    "107625 mhqs\n" +
                    "281871 mvwvdtd.dzf\n" +
                    "$ cd ..\n" +
                    "$ cd wbzvmz\n" +
                    "$ ls\n" +
                    "287371 tnwgfw\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mvjttqr\n" +
                    "$ ls\n" +
                    "dir dtf\n" +
                    "dir frcqdb\n" +
                    "dir hqdssf\n" +
                    "55645 slwshm.nwr\n" +
                    "dir zdzchmtq\n" +
                    "$ cd dtf\n" +
                    "$ ls\n" +
                    "121764 cvcbmcm\n" +
                    "279094 lncqsmj\n" +
                    "68484 slwshm.nwr\n" +
                    "278387 vjw\n" +
                    "$ cd ..\n" +
                    "$ cd frcqdb\n" +
                    "$ ls\n" +
                    "233216 lncqsmj.mbd\n" +
                    "217901 slwshm.nwr\n" +
                    "$ cd ..\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "dir dgdtm\n" +
                    "260973 qnt\n" +
                    "231311 thdrddd.zmj\n" +
                    "160399 vjw\n" +
                    "dir vpqjmmf\n" +
                    "197241 vqvzsv.nzg\n" +
                    "$ cd dgdtm\n" +
                    "$ ls\n" +
                    "dir cnshbf\n" +
                    "173312 vjw\n" +
                    "$ cd cnshbf\n" +
                    "$ ls\n" +
                    "72788 hqdssf.mhc\n" +
                    "122945 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vpqjmmf\n" +
                    "$ ls\n" +
                    "127969 hqdssf.stc\n" +
                    "267724 lncqsmj\n" +
                    "55890 mhqs.mql\n" +
                    "dir tsbtz\n" +
                    "$ cd tsbtz\n" +
                    "$ ls\n" +
                    "244065 qqfnbd.nqv\n" +
                    "59649 qttbcgd.vtj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zdzchmtq\n" +
                    "$ ls\n" +
                    "271886 lmphsmv\n" +
                    "dir nncghrr\n" +
                    "$ cd nncghrr\n" +
                    "$ ls\n" +
                    "180468 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd stdzptb\n" +
                    "$ ls\n" +
                    "19398 frsztqqz.mnc\n" +
                    "$ cd ..\n" +
                    "$ cd tcddd\n" +
                    "$ ls\n" +
                    "dir hqdssf\n" +
                    "71084 jvvl.rcs\n" +
                    "dir wnzhld\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "178907 gcslqrn\n" +
                    "$ cd ..\n" +
                    "$ cd wnzhld\n" +
                    "$ ls\n" +
                    "206073 hpdnj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zmrt\n" +
                    "$ ls\n" +
                    "dir bmfgjr\n" +
                    "dir bpztq\n" +
                    "dir chsh\n" +
                    "dir dpjhn\n" +
                    "288144 mhqs.zvb\n" +
                    "$ cd bmfgjr\n" +
                    "$ ls\n" +
                    "189592 jhmgm\n" +
                    "$ cd ..\n" +
                    "$ cd bpztq\n" +
                    "$ ls\n" +
                    "184906 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd chsh\n" +
                    "$ ls\n" +
                    "110753 jvvl.rcs\n" +
                    "dir lncqsmj\n" +
                    "dir njdgplj\n" +
                    "dir qhpplfnd\n" +
                    "190460 rjjrg.pjq\n" +
                    "144668 wfbvtfmr.flv\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "dir pzd\n" +
                    "$ cd pzd\n" +
                    "$ ls\n" +
                    "287833 vhfjdg.jrz\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd njdgplj\n" +
                    "$ ls\n" +
                    "dir cbvz\n" +
                    "dir vfhj\n" +
                    "$ cd cbvz\n" +
                    "$ ls\n" +
                    "dir rtpcsrf\n" +
                    "$ cd rtpcsrf\n" +
                    "$ ls\n" +
                    "240018 wfbvtfmr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vfhj\n" +
                    "$ ls\n" +
                    "152840 cbbrgc.wnq\n" +
                    "243656 hqdssf\n" +
                    "36325 hqdssf.fhn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd qhpplfnd\n" +
                    "$ ls\n" +
                    "206490 dfththnq.vnm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd dpjhn\n" +
                    "$ ls\n" +
                    "127322 rjjrg.pjq\n" +
                    "dir shzgg\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "165774 cvcbmcm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zqqtb\n" +
                    "$ ls\n" +
                    "dir ljnmbqvd\n" +
                    "60485 pcz.snl\n" +
                    "274930 slwshm.nwr\n" +
                    "131928 vqm\n" +
                    "51341 wfbvtfmr.stc\n" +
                    "$ cd ljnmbqvd\n" +
                    "$ ls\n" +
                    "268048 bdbjn\n" +
                    "dir fjh\n" +
                    "dir qpclpbz\n" +
                    "dir rqpljw\n" +
                    "dir tzjftf\n" +
                    "255809 vjw\n" +
                    "243604 wfbvtfmr.rzb\n" +
                    "$ cd fjh\n" +
                    "$ ls\n" +
                    "180021 hqdssf.rqs\n" +
                    "$ cd ..\n" +
                    "$ cd qpclpbz\n" +
                    "$ ls\n" +
                    "109952 blmm.nsv\n" +
                    "dir dltbt\n" +
                    "dir htbzp\n" +
                    "287761 mfbd\n" +
                    "dir mhqs\n" +
                    "dir vgbvrwvn\n" +
                    "dir wmhd\n" +
                    "$ cd dltbt\n" +
                    "$ ls\n" +
                    "205121 dfzlcv.hjf\n" +
                    "56409 hpqhhmb.sss\n" +
                    "233277 hqdssf.qcb\n" +
                    "187838 mbmcfhf.fnj\n" +
                    "dir rrqjn\n" +
                    "dir swrgj\n" +
                    "151306 tqjzq.dmg\n" +
                    "$ cd rrqjn\n" +
                    "$ ls\n" +
                    "240447 lncqsmj\n" +
                    "$ cd ..\n" +
                    "$ cd swrgj\n" +
                    "$ ls\n" +
                    "dir lnnwdbt\n" +
                    "dir sgjsn\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd lnnwdbt\n" +
                    "$ ls\n" +
                    "213111 tnn.bhq\n" +
                    "$ cd ..\n" +
                    "$ cd sgjsn\n" +
                    "$ ls\n" +
                    "dir wpprqhs\n" +
                    "$ cd wpprqhs\n" +
                    "$ ls\n" +
                    "dir hqdssf\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "209286 qbdfzdzw.sgp\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "199298 hqdssf.chs\n" +
                    "123953 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd htbzp\n" +
                    "$ ls\n" +
                    "dir bmfr\n" +
                    "68162 bzwcswr.mlv\n" +
                    "284074 dtzwf\n" +
                    "270421 jpsvh.ncc\n" +
                    "dir mwbvz\n" +
                    "95631 shzgg.tbf\n" +
                    "44434 vjw\n" +
                    "$ cd bmfr\n" +
                    "$ ls\n" +
                    "19937 shzgg\n" +
                    "$ cd ..\n" +
                    "$ cd mwbvz\n" +
                    "$ ls\n" +
                    "156668 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "111821 ftrv.zcw\n" +
                    "dir jsnlcn\n" +
                    "dir mhqs\n" +
                    "dir rjdwmb\n" +
                    "dir sfmrwgd\n" +
                    "$ cd jsnlcn\n" +
                    "$ ls\n" +
                    "7527 smqts.cnc\n" +
                    "dir svdw\n" +
                    "$ cd svdw\n" +
                    "$ ls\n" +
                    "173846 vjtrp\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "dir hswdjgg\n" +
                    "30326 rjjrg.pjq\n" +
                    "$ cd hswdjgg\n" +
                    "$ ls\n" +
                    "285037 jvvl.rcs\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rjdwmb\n" +
                    "$ ls\n" +
                    "219498 twl.lmw\n" +
                    "$ cd ..\n" +
                    "$ cd sfmrwgd\n" +
                    "$ ls\n" +
                    "168290 ltwpm.fjl\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vgbvrwvn\n" +
                    "$ ls\n" +
                    "dir bswv\n" +
                    "dir hqdssf\n" +
                    "dir mhqs\n" +
                    "$ cd bswv\n" +
                    "$ ls\n" +
                    "121032 cncdt\n" +
                    "dir jrbmlg\n" +
                    "dir lncqsmj\n" +
                    "dir mhmgn\n" +
                    "65380 rjjrg.pjq\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd jrbmlg\n" +
                    "$ ls\n" +
                    "37975 shzgg.bnq\n" +
                    "$ cd ..\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "114558 jvvl.rcs\n" +
                    "dir mhqs\n" +
                    "dir nfn\n" +
                    "dir nhlbfq\n" +
                    "141816 phtt.phj\n" +
                    "181530 vjw\n" +
                    "224510 zmzp.jwg\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "dir wpnw\n" +
                    "$ cd wpnw\n" +
                    "$ ls\n" +
                    "286077 lncqsmj.cld\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd nfn\n" +
                    "$ ls\n" +
                    "269740 cvcbmcm\n" +
                    "87123 mhqs.nst\n" +
                    "61029 mhqs.vhb\n" +
                    "211712 slwshm.nwr\n" +
                    "$ cd ..\n" +
                    "$ cd nhlbfq\n" +
                    "$ ls\n" +
                    "247458 lncqsmj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhmgn\n" +
                    "$ ls\n" +
                    "85806 vjw\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "275888 cvvhvg.pwv\n" +
                    "151703 gnbm\n" +
                    "61419 grcfbwdp\n" +
                    "150382 gzdmzj.wpc\n" +
                    "dir mtrqcwrd\n" +
                    "112827 nbtjlnt.srg\n" +
                    "$ cd mtrqcwrd\n" +
                    "$ ls\n" +
                    "273564 shzgg.tbm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "72227 ccvwdrs.dsf\n" +
                    "141028 dctbz.spr\n" +
                    "dir shzgg\n" +
                    "277461 slwshm.nwr\n" +
                    "94390 stgp.trm\n" +
                    "24297 vgdzsrrl.dhg\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "143437 lncqsmj.vpl\n" +
                    "78418 pbdwbmtd.nlp\n" +
                    "28173 vlfj.tps\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "97312 jvvl.rcs\n" +
                    "dir qqfbsz\n" +
                    "42472 rjjrg.pjq\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd qqfbsz\n" +
                    "$ ls\n" +
                    "dir lncqsmj\n" +
                    "dir mhqs\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "186873 wfbvtfmr.lfh\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "234441 gsjwsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "55214 dgf\n" +
                    "dir fdtz\n" +
                    "6756 jvvl.rcs\n" +
                    "226995 rpsjvr.wpb\n" +
                    "68993 vjw\n" +
                    "$ cd fdtz\n" +
                    "$ ls\n" +
                    "dir rdzsjm\n" +
                    "$ cd rdzsjm\n" +
                    "$ ls\n" +
                    "dir mjgfpvc\n" +
                    "$ cd mjgfpvc\n" +
                    "$ ls\n" +
                    "dir vfcq\n" +
                    "$ cd vfcq\n" +
                    "$ ls\n" +
                    "dir fmtfm\n" +
                    "$ cd fmtfm\n" +
                    "$ ls\n" +
                    "200137 dvbqjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wmhd\n" +
                    "$ ls\n" +
                    "dir hnn\n" +
                    "dir rzt\n" +
                    "$ cd hnn\n" +
                    "$ ls\n" +
                    "16335 wfbvtfmr.vhw\n" +
                    "262909 zgtln.dwc\n" +
                    "$ cd ..\n" +
                    "$ cd rzt\n" +
                    "$ ls\n" +
                    "285086 mhqs.vsg\n" +
                    "dir qnr\n" +
                    "$ cd qnr\n" +
                    "$ ls\n" +
                    "290611 llnp.hsh\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rqpljw\n" +
                    "$ ls\n" +
                    "267152 jbv\n" +
                    "$ cd ..\n" +
                    "$ cd tzjftf\n" +
                    "$ ls\n" +
                    "258707 cnfb.dgv\n" +
                    "17087 hqdssf.dtd\n" +
                    "92075 nwvtww\n" +
                    "dir qblrnn\n" +
                    "dir tdg\n" +
                    "$ cd qblrnn\n" +
                    "$ ls\n" +
                    "dir brrqp\n" +
                    "dir frqcc\n" +
                    "25504 jvvl.rcs\n" +
                    "dir lncqsmj\n" +
                    "dir mhqs\n" +
                    "107908 rjjrg.pjq\n" +
                    "286918 rngbhp.ntg\n" +
                    "dir shzgg\n" +
                    "dir wcnh\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd brrqp\n" +
                    "$ ls\n" +
                    "270783 wfbvtfmr\n" +
                    "$ cd ..\n" +
                    "$ cd frqcc\n" +
                    "$ ls\n" +
                    "dir clj\n" +
                    "7502 lncqsmj\n" +
                    "dir mnsbr\n" +
                    "73859 scwltjwh.cqd\n" +
                    "29920 vjw\n" +
                    "dir wpplgrc\n" +
                    "$ cd clj\n" +
                    "$ ls\n" +
                    "31407 grm.pwv\n" +
                    "$ cd ..\n" +
                    "$ cd mnsbr\n" +
                    "$ ls\n" +
                    "dir wfbvtfmr\n" +
                    "99467 whsrtf.gff\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "81283 cvcbmcm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wpplgrc\n" +
                    "$ ls\n" +
                    "285496 lncqsmj.hwq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "8972 jvvl.rcs\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "119520 hqdssf.tbn\n" +
                    "$ cd ..\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "180825 lncqsmj.rsg\n" +
                    "23507 wfbvtfmr\n" +
                    "$ cd ..\n" +
                    "$ cd wcnh\n" +
                    "$ ls\n" +
                    "98662 tmwrh\n" +
                    "220468 wml.sht\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "283178 hqdssf.ctf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd tdg\n" +
                    "$ ls\n" +
                    "283636 cvcbmcm\n" +
                    "117742 gsqzjfn.pmp\n" +
                    "176660 lncqsmj\n" +
                    "dir pbdgbsts\n" +
                    "dir znvv\n" +
                    "$ cd pbdgbsts\n" +
                    "$ ls\n" +
                    "92092 vjw\n" +
                    "$ cd ..\n" +
                    "$ cd znvv\n" +
                    "$ ls\n" +
                    "41213 hqdssf.rnn\n" +
                    "dir pcttrtd\n" +
                    "$ cd pcttrtd\n" +
                    "$ ls\n" +
                    "16759 mhqs.gpb\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "19544 bcn.rws\n" +
                    "dir fqwpwcq\n" +
                    "dir hngpzst\n" +
                    "dir hqdssf\n" +
                    "dir shzgg\n" +
                    "dir vgjr\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd fqwpwcq\n" +
                    "$ ls\n" +
                    "269576 mhqs\n" +
                    "dir pmfv\n" +
                    "$ cd pmfv\n" +
                    "$ ls\n" +
                    "175725 jvvl.rcs\n" +
                    "29161 pnbm.mlr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd hngpzst\n" +
                    "$ ls\n" +
                    "18430 cvcbmcm\n" +
                    "dir fgzlgbm\n" +
                    "dir hqdssf\n" +
                    "378 rjjrg.pjq\n" +
                    "dir zjlnsztt\n" +
                    "$ cd fgzlgbm\n" +
                    "$ ls\n" +
                    "134751 dwlfbfjp\n" +
                    "dir hqdssf\n" +
                    "227313 jvvl.rcs\n" +
                    "dir lncqsmj\n" +
                    "dir mhqs\n" +
                    "dir qfjmtvpv\n" +
                    "dir qzm\n" +
                    "114934 shzgg.jdf\n" +
                    "187091 zpgbdnl.twq\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "87131 bdmdpdf.cqg\n" +
                    "dir fdc\n" +
                    "dir gwgp\n" +
                    "$ cd fdc\n" +
                    "$ ls\n" +
                    "116738 cvcbmcm\n" +
                    "$ cd ..\n" +
                    "$ cd gwgp\n" +
                    "$ ls\n" +
                    "dir qdvss\n" +
                    "dir rpwzw\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd qdvss\n" +
                    "$ ls\n" +
                    "111546 wqnsrnh\n" +
                    "$ cd ..\n" +
                    "$ cd rpwzw\n" +
                    "$ ls\n" +
                    "183614 lfjqvff\n" +
                    "129803 rdwd\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "266779 mhqs\n" +
                    "189793 slwshm.nwr\n" +
                    "279894 sncqtvwp.mff\n" +
                    "40520 vjw\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "257714 dtcjv.lsd\n" +
                    "69877 shzgg\n" +
                    "267645 vjw\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "257031 stsfrb.gvs\n" +
                    "$ cd ..\n" +
                    "$ cd qfjmtvpv\n" +
                    "$ ls\n" +
                    "dir mhqs\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "91378 wldhvhl\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd qzm\n" +
                    "$ ls\n" +
                    "dir shn\n" +
                    "$ cd shn\n" +
                    "$ ls\n" +
                    "dir jmmlsvdg\n" +
                    "$ cd jmmlsvdg\n" +
                    "$ ls\n" +
                    "41383 rnlgl.vjv\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "dir mzj\n" +
                    "$ cd mzj\n" +
                    "$ ls\n" +
                    "169639 sgdn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zjlnsztt\n" +
                    "$ ls\n" +
                    "78315 jfj.sgg\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "906 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "163015 mhqs.cnb\n" +
                    "$ cd ..\n" +
                    "$ cd vgjr\n" +
                    "$ ls\n" +
                    "129546 hclg.vbl\n" +
                    "286437 hqdssf\n" +
                    "dir lncqsmj\n" +
                    "1552 nhgwff\n" +
                    "279693 qmpdlw\n" +
                    "83645 slwshm.nwr\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "233355 fhpjbpjl.tbh\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "218452 jvvl.rcs\n" +
                    "47525 rjjrg.pjq\n" +
                    "109880 tctz.rnd\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd jwphbz\n" +
                    "$ ls\n" +
                    "dir bqbwds\n" +
                    "dir mwnzw\n" +
                    "113414 qgdsgfg.ngj\n" +
                    "141013 qtp\n" +
                    "10795 slwshm.nwr\n" +
                    "$ cd bqbwds\n" +
                    "$ ls\n" +
                    "18049 shzgg\n" +
                    "$ cd ..\n" +
                    "$ cd mwnzw\n" +
                    "$ ls\n" +
                    "169353 dtqlr\n" +
                    "dir hlrgzlph\n" +
                    "dir mhqs\n" +
                    "238527 vjw\n" +
                    "$ cd hlrgzlph\n" +
                    "$ ls\n" +
                    "249097 hqdssf\n" +
                    "86490 pwdmzwb\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "176337 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd lncqsmj\n" +
                    "$ ls\n" +
                    "dir jmsw\n" +
                    "dir mcgm\n" +
                    "dir mpc\n" +
                    "233050 rjjrg.pjq\n" +
                    "30757 shzgg\n" +
                    "250575 slwshm.nwr\n" +
                    "$ cd jmsw\n" +
                    "$ ls\n" +
                    "dir hqdssf\n" +
                    "dir nmsbdqhm\n" +
                    "dir wfbvtfmr\n" +
                    "$ cd hqdssf\n" +
                    "$ ls\n" +
                    "44806 lvzm\n" +
                    "$ cd ..\n" +
                    "$ cd nmsbdqhm\n" +
                    "$ ls\n" +
                    "dir czzp\n" +
                    "dir gcp\n" +
                    "dir gsrnmq\n" +
                    "dir rmngvc\n" +
                    "dir vpfq\n" +
                    "dir vpzvb\n" +
                    "dir wccgblsq\n" +
                    "43981 wfbvtfmr\n" +
                    "$ cd czzp\n" +
                    "$ ls\n" +
                    "95330 slwshm.nwr\n" +
                    "82000 wfbvtfmr\n" +
                    "$ cd ..\n" +
                    "$ cd gcp\n" +
                    "$ ls\n" +
                    "229303 ddsppspd.fcn\n" +
                    "$ cd ..\n" +
                    "$ cd gsrnmq\n" +
                    "$ ls\n" +
                    "dir hrsstlt\n" +
                    "dir rzc\n" +
                    "40890 sfpt\n" +
                    "$ cd hrsstlt\n" +
                    "$ ls\n" +
                    "18477 mnpc.tmh\n" +
                    "$ cd ..\n" +
                    "$ cd rzc\n" +
                    "$ ls\n" +
                    "33078 dmfwf\n" +
                    "149792 wfbvtfmr.jnz\n" +
                    "dir zsnmz\n" +
                    "$ cd zsnmz\n" +
                    "$ ls\n" +
                    "dir gzsnl\n" +
                    "69834 lplszs.rvg\n" +
                    "213489 slwshm.nwr\n" +
                    "dir vrnqh\n" +
                    "$ cd gzsnl\n" +
                    "$ ls\n" +
                    "109060 jvvl.rcs\n" +
                    "122307 svtppnvt.wrz\n" +
                    "$ cd ..\n" +
                    "$ cd vrnqh\n" +
                    "$ ls\n" +
                    "94389 jvvl.rcs\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rmngvc\n" +
                    "$ ls\n" +
                    "dir jljbrrgg\n" +
                    "$ cd jljbrrgg\n" +
                    "$ ls\n" +
                    "128554 cvcbmcm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vpfq\n" +
                    "$ ls\n" +
                    "73628 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd vpzvb\n" +
                    "$ ls\n" +
                    "265609 rjjrg.pjq\n" +
                    "$ cd ..\n" +
                    "$ cd wccgblsq\n" +
                    "$ ls\n" +
                    "110134 hffzwm.zfr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wfbvtfmr\n" +
                    "$ ls\n" +
                    "209356 jvvl.rcs\n" +
                    "286105 mtsb.bwd\n" +
                    "234687 vcgnlc.cft\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mcgm\n" +
                    "$ ls\n" +
                    "203407 cvcbmcm\n" +
                    "$ cd ..\n" +
                    "$ cd mpc\n" +
                    "$ ls\n" +
                    "dir shzgg\n" +
                    "$ cd shzgg\n" +
                    "$ ls\n" +
                    "dir bnb\n" +
                    "$ cd bnb\n" +
                    "$ ls\n" +
                    "232449 lncqsmj.czs\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "116955 jvvl.rcs\n" +
                    "$ cd ..\n" +
                    "$ cd trwqgzsb\n" +
                    "$ ls\n" +
                    "88439 jgphdrnq\n" +
                    "$ cd ..\n" +
                    "$ cd wbsph\n" +
                    "$ ls\n" +
                    "dir mhqs\n" +
                    "dir vgh\n" +
                    "$ cd mhqs\n" +
                    "$ ls\n" +
                    "133965 rfh.zdb\n" +
                    "$ cd ..\n" +
                    "$ cd vgh\n" +
                    "$ ls\n" +
                    "36092 rjjrg.pjq";
//            "$ cd /\n" +
//            "$ ls\n" +
//            "dir a\n" +
//            "14848514 b.txt\n" +
//            "8504156 c.dat\n" +
//            "dir d\n" +
//            "$ cd a\n" +
//            "$ ls\n" +
//            "dir e\n" +
//            "29116 f\n" +
//            "2557 g\n" +
//            "62596 h.lst\n" +
//            "$ cd e\n" +
//            "$ ls\n" +
//            "584 i\n" +
//            "$ cd ..\n" +
//            "$ cd ..\n" +
//            "$ cd d\n" +
//            "$ ls\n" +
//            "4060174 j\n" +
//            "8033020 d.log\n" +
//            "5626152 d.ext\n" +
//            "7214296 k";
}
