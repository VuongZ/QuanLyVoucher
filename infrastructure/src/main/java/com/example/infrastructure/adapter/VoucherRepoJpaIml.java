package com.example.infrastructure.adapter;

import com.example.domain.entity.voucher;
import com.example.domain.repository.voucherRepo;
import com.example.infrastructure.Repositories.VoucherJpaRepository;
import com.example.infrastructure.mapper.VoucherMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VoucherRepoJpaIml implements voucherRepo {
    private final VoucherJpaRepository repo;

    public VoucherRepoJpaIml(VoucherJpaRepository repo)
    {
        this.repo=repo;
    }

    @Override
    public List<voucher> GetAll() {
        return repo.findAll().stream().map(VoucherMapper::toDomain).toList();
    }

    @Override
    public Optional<voucher> findById(Integer id) {
        return repo.findById(id).map(VoucherMapper::toDomain);
    }

    @Override
    public Optional<voucher> findByCode(String code) {
        return repo.findByCode(code).map(VoucherMapper::toDomain);
    }

    @Override
    public voucher save(voucher vc) {
        return VoucherMapper.toDomain(repo.save(VoucherMapper.toJpa(vc)));
    }

    @Override
    public void update(Integer id, voucher vc) {
        vc.setId(id);
        save(vc);

    }

    @Override
    public void delete(voucher vc) {
repo.deleteById(vc.getId());
    }
}
